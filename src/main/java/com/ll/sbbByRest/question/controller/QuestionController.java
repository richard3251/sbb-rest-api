package com.ll.sbbByRest.question.controller;

import com.ll.sbbByRest.exceptions.ServiceException;
import com.ll.sbbByRest.question.dto.QuestionDto;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.form.QuestionForm;
import com.ll.sbbByRest.question.service.QuestionService;
import com.ll.sbbByRest.rs.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
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
    public QuestionDto getQuestion(
            @PathVariable("id") Integer id) {

        return this.questionService.findById(id)
                .map(QuestionDto :: new)
                .orElseThrow(
                        () -> new ServiceException("404-1", "게시물을 찾을 수 없습니다.")
                );
    }

    @PostMapping
    public RsData<QuestionDto> questionCreate(@RequestBody @Valid QuestionForm questionForm) {
        Question question = this.questionService.create(questionForm.getTitle(), questionForm.getContent());

        return new RsData<>(
                "201-1",
                "%d번 글이 작성되었습니다.".formatted(question.getId()),
                new QuestionDto(question)
        );
    }

    @PutMapping("/{id}")
    public RsData<QuestionDto> modify(
            @PathVariable("id") Integer id,
            @RequestBody @Valid QuestionForm questionForm
    ) {
        Question question = this.questionService.findById(id).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(id))
        );

        this.questionService.modify(question, questionForm.getTitle(), questionForm.getContent());

        return new RsData<>(
                "200-1",
                "%d번 글이 수정되었습니다.".formatted(id),
                new QuestionDto(question)
        );

    }

    @DeleteMapping("/{id}")
    public RsData<Void> delete(
            @PathVariable("id") Integer id
    ) {
        Question question = this.questionService.findById(id).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(id))
        );

        this.questionService.delete(question);

        return new RsData<>(
                "200-1",
                "%d번 글이 삭제되었습니다.".formatted(id)
        );


    }







}
