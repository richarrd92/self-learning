package com.cooksys.quiz_api.services.impl;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizMapper quizMapper;
    private final QuestionMapper questionMapper;

    @Override
    public List<QuizResponseDto> getAllQuizzes() {
        // Get all non-deleted quizzes from the repository.
        // Map the quizzes to DTOs and return them.
        return quizMapper.entitiesToDtos(quizRepository.findAllByDeletedFalse());
    }

    @Override
    public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
        Optional<Quiz> optionalQuiz = quizRepository.findByName(quizRequestDto.getName());
        Quiz quiz;

        // Case 1: A quiz with the given name already exists
        if (optionalQuiz.isPresent()) {
            quiz = optionalQuiz.get();

            // If the quiz is still active (not deleted), we cannot reuse the name
            if (!quiz.isDeleted()) {
                throw new IllegalArgumentException("Quiz name already exists.");
            }

            // If the quiz was soft-deleted:
            //   - Reactivate it
            //   - Remove its old questions so we can attach the new ones from the request
            quiz.setDeleted(false);
            quiz.clearQuestions();
        } else {
            // Case 2: No quiz with this name exists
            // Create a fresh quiz entity with the provided name
            quiz = new Quiz();
            quiz.setName(quizRequestDto.getName());
        }

        // Map DTO → entity (build questions and answers from request)
        Quiz mappedQuiz = quizMapper.requestDtoToEntity(quizRequestDto);

        // Attach mapped questions and answers to the quiz
        for (Question question : mappedQuiz.getQuestions()) {
            quiz.addQuestion(question); // also sets question.setQuiz(this)
            for (Answer answer : question.getAnswers()) {
                answer.setQuestion(question); // maintain parent reference
            }
        }

        // Save the quiz (cascades persist questions/answers) and return the mapped response DTO
        return quizMapper.entityToDto(quizRepository.saveAndFlush(quiz));
    }

    @Override
    public QuizResponseDto deleteQuizByName(String name) {
        Optional<Quiz> optionalQuiz = quizRepository.findByName(name);

        if (optionalQuiz.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found");
        }

        Quiz quizToDelete = optionalQuiz.get();

        if (quizToDelete.isDeleted()) {
            throw new IllegalArgumentException("Quiz already deleted");
        }

        quizToDelete.setDeleted(true);
        for (Question question : quizToDelete.getQuestions()) {
            question.setDeleted(true);
        }

        quizRepository.save(quizToDelete);
        return quizMapper.entityToDto(quizToDelete);
    }

    @Override
    public QuizResponseDto updateQuizByName(String name, String newName) {
        Optional<Quiz> optionalQuiz = quizRepository.findByName(name);
        if (optionalQuiz.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found");
        }

        Quiz quiz = optionalQuiz.get();
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted");
        }

        quiz.setName(newName);
        quizRepository.save(quiz);
        return quizMapper.entityToDto(quiz);
    }

    @Override
    public QuestionResponseDto getRandomQuestionFromQuiz(String quizName) {
        Optional<Quiz> optionalQuiz = quizRepository.findByName(quizName);
        if (optionalQuiz.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found");
        }

        // Quiz is active
        Quiz quiz = optionalQuiz.get();
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted");
        }

        // filter out deleted questions
        List<Question> activeQuestions = new ArrayList<>();
        for (Question question : quiz.getQuestions()) {
            // only add active questions
            if (!question.isDeleted()) {
                activeQuestions.add(question);
            }
        }

        if (activeQuestions.isEmpty()) {
            throw new IllegalArgumentException("Quiz does not contain any active questions");
        }

        // get random question from active questions
        Random rand = new Random();
        int numberOfActiveQuestions = activeQuestions.size();
        int randomIndex = rand.nextInt(numberOfActiveQuestions);
        Question question = activeQuestions.get(randomIndex);
        return questionMapper.entityToDto(question);
    }

    @Override
    public QuizResponseDto addQuestionToQuiz(QuestionRequestDto questionRequestDto, String quizName) {
        Optional<Quiz> optionalQuiz = quizRepository.findByName(quizName);

        // find matching quiz
        if (optionalQuiz.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found");
        }

        Quiz quiz = optionalQuiz.get();

        // quiz is soft-deleted
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted");
        }

        // active matching quiz found
        // convert Dto to entity
        Question questionToAdd = questionMapper.dtoToEntity(questionRequestDto);

        // Ensure all answers are properly linked to the question
        if (questionToAdd.getAnswers() != null) {
            // For each answer, set its parent question
            for (Answer answer : questionToAdd.getAnswers()) {
                answer.setQuestion(questionToAdd);
            }
        }
        
        // Add the question to the quiz using helper (sets quiz in question automatically)
        quiz.addQuestion(questionToAdd);

        // Save quiz (cascades to question and answers)
        quizRepository.save(quiz);

        // Return DTO
        return quizMapper.entityToDto(quiz);
    }

    @Override
    public QuestionResponseDto deleteQuestionFromQuiz(String quizName, Long questionId) {
        // validate quiz name
        Optional<Quiz> optionalQuiz = quizRepository.findByName(quizName);
        if (optionalQuiz.isEmpty()) {
            throw new IllegalArgumentException("Quiz not found");
        }

        // quiz is deleted
        Quiz quiz = optionalQuiz.get();
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted");
        }

        Question questionToDelete = null;

        // active matching quiz found
        for (Question question : quiz.getQuestions()) {
            // validate question id
            if (question.getId().equals(questionId)) {
                // question is deleted
                if (question.isDeleted()) {
                    throw new IllegalArgumentException("Question is deleted");
                }

                // soft-delete question
                question.setDeleted(true);
                questionRepository.save(question);
                questionToDelete = question;
                break;
            }
        }

        if (questionToDelete == null) {
            throw new IllegalArgumentException("Question not found");
        }

        return questionMapper.entityToDto(questionToDelete);
    }
}
