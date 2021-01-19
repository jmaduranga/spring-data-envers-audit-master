package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.QuestionDTO;
import com.wiley.resultcontext.entity.Assessment;
import com.wiley.resultcontext.entity.Question;
import com.wiley.resultcontext.repository.AssessmentRepository;
import com.wiley.resultcontext.repository.QuestionRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        Question question = modelMapper.map(questionDTO, Question.class);
        Assessment assessment = assessmentRepository.findByAssessmentId(questionDTO.getAssessmentId());
        if(assessment != null){
            question.setAssessmentKey(assessment.getKey());
            question.setAssessment(assessment);
            return modelMapper.map(questionRepository.save(question), QuestionDTO.class);
        }
        return null;
    }

    @Override
    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        Question oldQuestion = questionRepository.findByQuestionId(questionDTO.getQuestionId());
        if (oldQuestion != null) {
            Question question = modelMapper.map(questionDTO, Question.class);

            question.setKey(oldQuestion.getKey());
            question.setCreatedBy(oldQuestion.getCreatedBy());
            question.setCreationDate(oldQuestion.getCreationDate());
            question.setVersion(oldQuestion.getVersion());

            return modelMapper.map(questionRepository.save(question), QuestionDTO.class);
        }
        return modelMapper.map(questionRepository
                .save(modelMapper.map(questionDTO, Question.class)), QuestionDTO.class);

    }
}
