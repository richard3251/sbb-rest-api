package com.ll.sbbByRest.question.controller;

import com.ll.sbbByRest.question.dto.QuestionDto;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.form.QuestionForm;
import com.ll.sbbByRest.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public List<QuestionDto> questionlist() {

        return this.questionService
                .getList()
                .stream()
                .map(QuestionDto :: new)
                .toList();
    }

    @GetMapping("/page")
    public Page<QuestionDto> questionPageList(
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Question> paging = this.questionService.getList(page);

        return new PageImpl<>(
                paging.stream()
                        .map(QuestionDto :: new)
                        .toList(),
                paging.getPageable(),
                paging.getTotalElements()
        );
    }

    @GetMapping("/{id}")
    public QuestionDto getQuestion(@PathVariable("id") Integer id) {
        Question q = this.questionService.getQuestion(id);

        return new QuestionDto(q);
    }

    @PostMapping
    public QuestionDto questionCreate(@RequestBody @Valid QuestionForm questionForm) {
        Question q = this.questionService.create(questionForm.getTitle(), questionForm.getContent());

        return new QuestionDto(q);
    }





}
