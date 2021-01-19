package com.wiley.resultcontext.repository;

import com.wiley.resultcontext.entity.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findByQuestionId(String questionId);
}
