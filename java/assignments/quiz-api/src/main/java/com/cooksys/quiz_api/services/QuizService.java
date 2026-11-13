package com.cooksys.quiz_api.services;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;

public interface QuizService {

  List<QuizResponseDto> getAllQuizzes();
  QuizResponseDto createQuiz(QuizRequestDto quizRequestDto);
  QuizResponseDto deleteQuizByName(String name);
  QuizResponseDto renameQuiz(String currentName, String newName);
  QuestionResponseDto getRandomQuestion(String quizName);
  QuizResponseDto addQuestion(String quizName, QuestionRequestDto questionRequestDto);
  QuestionResponseDto deleteQuestion(String quizName, Long questionId);

}
