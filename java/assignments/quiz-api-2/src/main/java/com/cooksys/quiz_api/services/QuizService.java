package com.cooksys.quiz_api.services;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;

import java.util.List;

public interface QuizService {

    List<QuizResponseDto> getAllQuizzes();

    QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);

    QuizResponseDto deleteQuizByName(String name);

    QuizResponseDto updateQuizByName(String name, String newName);

    QuestionResponseDto getRandomQuestionFromQuiz(String quizName);

    QuizResponseDto addQuestionToQuiz(QuestionRequestDto questionRequestDto, String quizName);

    QuestionResponseDto deleteQuestionFromQuiz(String quizName, Long questionId);
}
