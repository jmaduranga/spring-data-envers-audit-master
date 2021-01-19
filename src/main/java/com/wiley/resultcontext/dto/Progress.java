package com.wiley.resultcontext.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.wiley.resultcontext.service.QuestionService;
import lombok.Getter;
import lombok.Setter;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AssessmentDTO.class, name = "assessment"),
        @JsonSubTypes.Type(value = QuestionDTO.class, name = "question"),
        @JsonSubTypes.Type(value = AnswerDTO.class, name = "answer")
})
@Getter
@Setter
public abstract class Progress {

}
