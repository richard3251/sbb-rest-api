package com.ll.sbbByRest.question.service;

import com.ll.sbbByRest.question.entity.Question;
import com.ll.sbbByRest.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Sort sort = Sort.by(sorts);

        return this.questionRepository.findAll(sort);
    }

    public Page<Question> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.questionRepository.findAll(pageable);
    }

    public Optional<Question> findById(Integer id) {
        return this.questionRepository.findById(id);
    }

    public Question create(String title, String content) {
        Question q = new Question();
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());

        this.questionRepository.save(q);
        return q;
    }

    public void modify(Question question,String title, String content) {
        question.setTitle(title);
        question.setContent(content);

        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }


}
