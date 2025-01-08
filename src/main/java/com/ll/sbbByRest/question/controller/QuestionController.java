package com.ll.sbbByRest.question.controller;

import com.ll.sbbByRest.question.dto.QuestionDto;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<QuestionDto> list() {

        return this.questionService
                .getList()
                .stream()
                .map(QuestionDto :: new)
                .toList();
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestion(@PathVariable("id") Integer id) {
        Question q = this.questionService.getQuestion(id);

        return new QuestionDto(q);
    }





}
