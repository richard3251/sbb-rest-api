package com.ll.sbbByRest.question.dto;

import com.ll.sbbByRest.question.entity.Question;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class QuestionDto {

    private Integer id;

    private String title;

    private String content;

    private LocalDateTime createDate;

    public QuestionDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.createDate = question.getCreateDate();
    }



}
