package com.ll.sbbByRest.answer.controller;

import com.ll.sbbByRest.answer.answer.AnswerService;
import com.ll.sbbByRest.answer.dto.AnswerDto;
import com.ll.sbbByRest.answer.entity.Answer;
import com.ll.sbbByRest.answer.form.AnswerForm;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions/{postId}/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping
    public List<AnswerDto> answerList(@PathVariable("postId") Integer postId) {
        Question q = this.questionService.getQuestion(postId);

        return q.getAnswerList()
                .stream()
                .map(AnswerDto:: new)
                .toList();
    }

    @PostMapping
    @Transactional
    public String createAnswer(@PathVariable("postId") Integer postId,
                               @RequestBody @Valid AnswerForm answerForm) {
        Question q = this.questionService.getQuestion(postId);

        Answer answer = this.answerService.save(q, answerForm.getContent());

        return "%d번 댓글이 작성되었습니다.".formatted(answer.getId());
    }






}
