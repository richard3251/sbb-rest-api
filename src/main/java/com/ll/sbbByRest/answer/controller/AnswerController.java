package com.ll.sbbByRest.answer.controller;

import com.ll.sbbByRest.answer.answer.AnswerService;
import com.ll.sbbByRest.answer.entity.Answer;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.service.QuestionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/questions/{postId}/answer")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    record PostAnswerWriteReqBody (
            @NotBlank
            @Length(min = 2)
            String content
    ) {}

    @PostMapping()
    @Transactional
    public String createAnswer(@PathVariable("postId") Integer postId,
                               @RequestBody @Valid PostAnswerWriteReqBody reqBody) {
        Question q = this.questionService.getQuestion(postId);
        // 댓글 저장
        Answer answer = this.answerService.save(q, reqBody.content);

        return "%d번 댓글이 작성되었습니다.".formatted(answer.getId());
    }






}
