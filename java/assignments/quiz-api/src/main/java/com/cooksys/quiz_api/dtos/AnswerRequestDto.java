package com.cooksys.quiz_api.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AnswerRequestDto {

    private String text;

    private boolean correct;
}
