package com.cooksys.quiz_api.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerRequestDto {
    private String text;
    private boolean correct;
}
