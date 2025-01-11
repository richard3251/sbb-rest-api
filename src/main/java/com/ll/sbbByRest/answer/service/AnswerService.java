package com.ll.sbbByRest.answer.service;

import com.ll.sbbByRest.answer.entity.Answer;
import com.ll.sbbByRest.answer.repository.AnswerRepository;
import com.ll.sbbByRest.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public Answer save(Question question, String content) {
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());

        this.answerRepository.save(answer);
        return answer;
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());

        answerRepository.save(answer);
    }

    public Optional<Answer> findById(Integer id) {
       return answerRepository.findById(id);
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }



}
