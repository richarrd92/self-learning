package com.cooksys.quiz_api.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class QuizRequestDto {
    private String name;
    List<QuestionResponseDto> questions;
}
