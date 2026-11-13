package com.cooksys.quiz_api.controllers;

import com.cooksys.quiz_api.dtos.QuestionRequestDto;
import com.cooksys.quiz_api.dtos.QuestionResponseDto;
import com.cooksys.quiz_api.dtos.QuizRequestDto;
import com.cooksys.quiz_api.dtos.QuizResponseDto;
import com.cooksys.quiz_api.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponseDto createQuiz(@RequestBody QuizRequestDto quizRequestDto) {
        return quizService.createQuiz(quizRequestDto);
    }

    @DeleteMapping("/{name}")
    public QuizResponseDto deleteQuizByName(@PathVariable String name) {
        return quizService.deleteQuizByName(name);
    }

    @PatchMapping("/{name}/rename/{newName}")
    public QuizResponseDto updateQuizByName(@PathVariable String name, @PathVariable String newName) {
        return quizService.updateQuizByName(name, newName);
    }

    @GetMapping("/{name}/random")
    public QuestionResponseDto getRandomQuestionFromQuiz(@PathVariable("name") String quizName) {
        return quizService.getRandomQuestionFromQuiz(quizName);
    }

    @PatchMapping("/{name}/add")
    public QuizResponseDto addQuestionToQuiz(@RequestBody QuestionRequestDto questionRequestDto, @PathVariable("name") String quizName) {
        return quizService.addQuestionToQuiz(questionRequestDto, quizName);
    }

    @DeleteMapping("/{name}/delete/{questionID}")
    public QuestionResponseDto deleteQuestionFromQuiz(@PathVariable("name") String quizName, @PathVariable("questionID") Long questionId) {
        return quizService.deleteQuestionFromQuiz(quizName, questionId);
    }

}
