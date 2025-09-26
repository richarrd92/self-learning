package com.cooksys.quiz_api.controllers;

import java.util.List;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

  private final QuizService quizService;

  @GetMapping
  public List<QuizResponseDto> getAllQuizzes() {
    return quizService.getAllQuizzes();
  }

  // TODO: Implement the remaining 6 endpoints from the documentation.

    @PostMapping
    public ResponseEntity<QuizResponseDto> createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
        QuizResponseDto createdQuiz = quizService.createQuiz(quizRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @DeleteMapping("/{name}")
    public QuizResponseDto deleteQuiz(@PathVariable String name) {
        return quizService.deleteQuizByName(name);
    }

    @PatchMapping("/{name}/rename/{newName}")
    public QuizResponseDto renameQuiz(@PathVariable String name, @PathVariable String newName) {
        return quizService.renameQuiz(name, newName);
    }

    @GetMapping("/{name}/random")
    public QuestionResponseDto getRandomQuestion(@PathVariable String name) {
        return quizService.getRandomQuestion(name);
    }

    @PatchMapping("/{name}/add")
    public QuizResponseDto addQuestionToQuiz(@PathVariable String name, @RequestBody QuestionRequestDto questionRequestDto) {
        return quizService.addQuestion(name, questionRequestDto);
    }

    @DeleteMapping("/{name}/delete/{questionId}")
    public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable String name, @PathVariable Long questionId) {
        return quizService.deleteQuestion(name, questionId);
    }

}
