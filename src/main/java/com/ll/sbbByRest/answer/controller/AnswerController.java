package com.ll.sbbByRest.answer.controller;

import com.ll.sbbByRest.answer.dto.AnswerDto;
import com.ll.sbbByRest.answer.entity.Answer;
import com.ll.sbbByRest.answer.form.AnswerForm;
import com.ll.sbbByRest.answer.service.AnswerService;
import com.ll.sbbByRest.exceptions.ServiceException;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.service.QuestionService;
import com.ll.sbbByRest.rs.RsData;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions/{postId}/answers")
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;
//    private final UserService userService;

    @GetMapping
    public List<AnswerDto> answerList(@PathVariable("postId") Integer postId) {
        Question q = this.questionService.findById(postId).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(postId))
        );

        return q.getAnswerList()
                .stream()
                .map(AnswerDto:: new)
                .toList();
    }

    @GetMapping("/{id}")
    public AnswerDto getAnswer(
            @PathVariable("postId") Integer postId,
            @PathVariable("id") Integer id
    ) {
        Question question = this.questionService.findById(postId).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(postId))
        );

        return question
                .getAnswerById(id)
                .map(AnswerDto :: new)
                .orElseThrow(
                        () -> new ServiceException("404-2", "%d번 댓글은 존재하지 않습니다.".formatted(id))
                );

    }


    @PostMapping
    @Transactional
    public RsData<AnswerDto> createAnswer(@PathVariable("postId") Integer postId,
                                          @RequestBody @Valid AnswerForm answerForm) {
        Question question = this.questionService.findById(postId).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(postId))
        );

        Answer answer = this.answerService.save(question, answerForm.getContent());

        return new RsData<>(
                "201-1",
                "%d번 댓글이 생성되었습니다.".formatted(answer.getId()),
                new AnswerDto(answer)
        );
    }

    @PutMapping("{id}")
    public RsData<AnswerDto> modifyAnswer(
            @PathVariable("postId") Integer postId,
            @PathVariable("id") Integer id,
            @RequestBody @Valid AnswerForm answerForm
    ) {
        Question question = this.questionService.findById(postId).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(postId))
        );

        Answer answer = question.getAnswerById(id).orElseThrow(
                () -> new ServiceException("404-2", "%d번 댓글은 존재하지 않습니다.".formatted(id))
        );

        this.answerService.modify(answer, answerForm.getContent());

        return new RsData<>(
                "200-1",
                "%d번 댓글이 수정되었습니다.".formatted(id),
                new AnswerDto(answer)
        );

    }

    @DeleteMapping("/{id}")
    public RsData<Void> delete(
            @PathVariable("postId") Integer postId,
            @PathVariable("id") Integer id
    ) {

        this.questionService.findById(postId).orElseThrow(
                () -> new ServiceException("404-1", "%d번 글은 존재하지 않습니다.".formatted(postId))
        );

        Answer answer = this.answerService.findById(id).orElseThrow(
                () -> new ServiceException("404-2", "%d번 댓글은 존재하지 않습니다.".formatted(id))
        );

        this.answerService.delete(answer);

        return new RsData<>(
                "200-1",
                "%d번 댓글이 삭제 되었습니다.".formatted(id)
        );
    }











}
