package com.ll.sbbByRest.answer.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class AnswerForm {

    @NotBlank(message = "내용은 필수항목입니다.")
    @Length(min = 2)
    private String content;

}
