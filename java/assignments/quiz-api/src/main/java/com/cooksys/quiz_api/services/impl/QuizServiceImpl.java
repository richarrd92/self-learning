package com.cooksys.quiz_api.services.impl;


import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.cooksys.quiz_api.dtos.*;
import com.cooksys.quiz_api.entities.Answer;
import com.cooksys.quiz_api.entities.Question;
import com.cooksys.quiz_api.entities.Quiz;
import com.cooksys.quiz_api.mappers.QuestionMapper;
import com.cooksys.quiz_api.repositories.AnswerRepository;
import com.cooksys.quiz_api.repositories.QuestionRepository;
import org.springframework.stereotype.Service;

import com.cooksys.quiz_api.mappers.QuizMapper;
import com.cooksys.quiz_api.repositories.QuizRepository;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

  private final QuizRepository quizRepository;
  private final QuizMapper quizMapper;
  private final QuestionMapper questionMapper;
  private final QuestionRepository questionRepository;
  private final AnswerRepository answerRepository;

  @Override
  public List<QuizResponseDto> getAllQuizzes() {
    // Get all non-deleted quizzes from the repository.
    // Map the quizzes to DTOs and return them.
    return quizMapper.entitiesToDtos(quizRepository.findAllByDeletedFalse());
  }

    @Override
    public QuizResponseDto createQuiz(QuizRequestDto quizRequestDto) {
        // Check for existing quiz
        Optional<Quiz> optionalQuiz = quizRepository.findByName(quizRequestDto.getName());

        Quiz quiz;
        if (optionalQuiz.isPresent()) {
            quiz = optionalQuiz.get();
            // Quiz exists and is active → cannot create duplicate
            if (!quiz.isDeleted()) {
                throw new IllegalArgumentException("Quiz name already exists.");
            }
            // Reactivate deleted quiz
            quiz.setDeleted(false);
            quiz.getQuestions().clear();  // remove old questions/answers
        } else {
            // Quiz does not exist → create a new entity
            quiz = new Quiz();
            quiz.setName(quizRequestDto.getName());
        }

        // Map DTO → entity (nested questions/answers)
        Quiz mappedQuiz = quizMapper.requestDtoToEntity(quizRequestDto);
        quiz.setQuestions(mappedQuiz.getQuestions());

        // save Quiz first
        quizRepository.save(quiz);

        // save each Question and Answer
        for (Question q : quiz.getQuestions()) {
            q.setQuiz(quiz);
            // Save question
            questionRepository.save(q);
            if (q.getAnswers() != null) {
                for (Answer a : q.getAnswers()) {
                    a.setQuestion(q);
                    answerRepository.save(a);
                }
            }
        }

        QuizResponseDto response = quizMapper.entityToDto(quiz);

        // Ensure created timestamps are set
        //    - Quiz 'created' timestamp
        if (response.getCreated() == null) {
            response.setCreated(new Timestamp(System.currentTimeMillis()));
        }

        //    - Question 'created' timestamps
        for (QuestionResponseDto q : response.getQuestions()) {
            if (q.getCreated() == null) {
                q.setCreated(new Timestamp(System.currentTimeMillis()));
            }

            // Ensure answer text is never null to satisfy API schema
            for (AnswerResponseDto a : q.getAnswers()) {
                if (a.getText() == null) {
                    a.setText("");
                }
            }
        }

        // Return the fully populated response DTO
        return response;
    }

    @Override
    public QuizResponseDto deleteQuizByName(String name) {
        // Find the quiz by name
        Quiz quiz = quizRepository.findByName(name).orElse(null);
        if (quiz == null){
            throw new IllegalArgumentException("Quiz not found.");
        }

        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is already deleted.");
        }

        // Mark quiz as deleted
        quiz.setDeleted(true);

        // Also mark all associated questions as deleted
        if (quiz.getQuestions() != null) {
            for (var question : quiz.getQuestions()) {
                question.setDeleted(true);
            }
        }

        // Save changes
        quizRepository.save(quiz);

        // Return the quiz data prior to deletion
        return quizMapper.entityToDto(quiz);
    }

    @Override
    public QuizResponseDto renameQuiz(String currentName, String newName) {
        // Find existing quiz by current name
        Quiz quiz = quizRepository.findByName(currentName).orElse(null);
        if (quiz == null) {
            throw new IllegalArgumentException("Quiz not found.");
        }

        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Cannot rename a deleted quiz.");
        }

        // Check if new name is already taken by another active quiz
        Optional<Quiz> quizWithNewName = quizRepository.findByName(newName);
        if (quizWithNewName.isPresent() && !quizWithNewName.get().isDeleted()) {
            throw new IllegalArgumentException("A quiz with the new name already exists.");
        }

        // Update name
        quiz.setName(newName);

        // Save changes
        Quiz savedQuiz = quizRepository.save(quiz);

        // Return updated quiz
        return quizMapper.entityToDto(savedQuiz);
    }

    @Override
    public QuestionResponseDto getRandomQuestion(String quizName) {
        // Find the quiz by name
        Quiz quiz = quizRepository.findByName(quizName).orElse(null);
        if (quiz == null) {
            throw new IllegalArgumentException("Quiz not found.");
        }

        // Check if quiz is deleted
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted.");
        }

        // Filter out deleted questions
        List<Question> activeQuestions = new ArrayList<>();
        for (Question q : quiz.getQuestions()) {
            if (!q.isDeleted()) {
                activeQuestions.add(q);
            }
        }

        if (activeQuestions.isEmpty()) {
            throw new IllegalArgumentException("No active questions available for this quiz.");
        }

        // Randomly select a question
        Random random = new Random();
        Question selected = activeQuestions.get(random.nextInt(activeQuestions.size()));

        // Map to DTO and return
        return questionMapper.entityToDto(selected);
    }

    @Override
    public QuizResponseDto addQuestion(String quizName, QuestionRequestDto questionRequestDto) {
        // Find the quiz by name
        Quiz quiz = quizRepository.findByName(quizName)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found."));

        // Check if quiz is deleted
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted.");
        }

        // Map the QuestionRequestDto → Question entity
        Question newQuestion = questionMapper.requestDtoToEntity(questionRequestDto);

        // Set back-reference to parent quiz
        newQuestion.setQuiz(quiz);

        // Set back-references for answers
        if (newQuestion.getAnswers() != null) {
            for (Answer a : newQuestion.getAnswers()) {
                a.setQuestion(newQuestion);
            }
        }

        // Manually save question and answers
        questionRepository.save(newQuestion);
        if (newQuestion.getAnswers() != null) {
            for (Answer a : newQuestion.getAnswers()) {
                answerRepository.save(a);
            }
        }

        // Add question to quiz's list (optional)
        quiz.getQuestions().add(newQuestion);

        // Save the quiz (cascade persists questions and answers)
        Quiz savedQuiz = quizRepository.save(quiz);

        // Map to DTO
        QuizResponseDto response = quizMapper.entityToDto(savedQuiz);

        // Ensure timestamps are set
        if (response.getCreated() == null) {
            response.setCreated(new Timestamp(System.currentTimeMillis()));
        }
        for (QuestionResponseDto q : response.getQuestions()) {
            if (q.getCreated() == null) {
                q.setCreated(new Timestamp(System.currentTimeMillis()));
            }
            for (AnswerResponseDto a : q.getAnswers()) {
                if (a.getText() == null) a.setText("");
            }
        }

        return response;
    }

    @Override
    public QuestionResponseDto deleteQuestion(String quizName, Long questionId) {
        // Find the quiz by name
        Quiz quiz = quizRepository.findByName(quizName)
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found."));

        // Check if the quiz is deleted
        if (quiz.isDeleted()) {
            throw new IllegalArgumentException("Quiz is deleted.");
        }

        // Find the question by ID in this quiz
        Question questionToDelete = null;
        for (Question q : quiz.getQuestions()) {
            if (q.getId().equals(questionId)) {
                questionToDelete = q;
                break;
            }
        }

        if (questionToDelete == null) {
            throw new IllegalArgumentException("Question not found in this quiz.");
        }

        // Check if question is already deleted
        if (questionToDelete.isDeleted()) {
            throw new IllegalArgumentException("Question is already deleted.");
        }

        // Soft-delete the question
        questionToDelete.setDeleted(true);

        // Save the quiz (cascade will update the question)
        quizRepository.save(quiz);

        // Map to DTO
        QuestionResponseDto response = questionMapper.entityToDto(questionToDelete);

        // Ensure created timestamp is set
        if (response.getCreated() == null) {
            response.setCreated(new Timestamp(System.currentTimeMillis()));
        }

        // Ensure non-null answer texts
        for (AnswerResponseDto a : response.getAnswers()) {
            if (a.getText() == null) a.setText("");
        }

        return response;
    }


}
