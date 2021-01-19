package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.QuestionDTO;

public interface QuestionService {
    QuestionDTO addQuestion(QuestionDTO questionDTO);

    QuestionDTO updateQuestion(QuestionDTO questionDTO);
}
