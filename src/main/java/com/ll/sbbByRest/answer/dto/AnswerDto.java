package com.ll.sbbByRest.answer.dto;

import com.ll.sbbByRest.answer.entity.Answer;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AnswerDto {

    private Integer id;

    private String content;

    private LocalDateTime createDate;

    private Integer questionId;

    public AnswerDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.createDate = answer.getCreateDate();
        this.questionId = answer.getQuestion().getId();
    }



}
