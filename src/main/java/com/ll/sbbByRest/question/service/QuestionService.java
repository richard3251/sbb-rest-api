package com.ll.sbbByRest.question.service;

import com.ll.sbbByRest.exceptions.DataNotFoundException;
import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> oq = this.questionRepository.findById(id);
        if (oq.isPresent()) {
            return oq.get();
        } else {
            throw new DataNotFoundException("question not found");
        }

    }


}
