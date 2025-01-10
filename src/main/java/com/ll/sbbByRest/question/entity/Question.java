package com.ll.sbbByRest.question.entity;

import com.ll.sbbByRest.answer.entity.Answer;
import com.ll.sbbByRest.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 20)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;


    public Optional<Answer> getAnswerById(Integer answerId) {
        return answerList.stream()
                .filter(answer -> answer.getId() == answerId)
                .findFirst();
    }


}
