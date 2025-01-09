package com.ll.sbbByRest.question.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class QuestionForm {

    @NotBlank(message = "제목은 필수항목입니다.")
    @Length(max = 200)
    private String title;

    @NotBlank(message = "내용은 필수항목입니다.")
    @Length(min = 2)
    private String content;

}
