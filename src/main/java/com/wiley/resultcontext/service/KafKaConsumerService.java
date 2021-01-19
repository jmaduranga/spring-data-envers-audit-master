package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafKaConsumerService {

    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private QuestionService questionService;


    @KafkaListener(topics = "${testmodule.kafka.topic}")
    public void consume(Progress progress, Acknowledgment acknowledgment, @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("consumer received progress message {}, ###########offset {}", progress.getClass().getName(), offset);
        if(progress instanceof AssessmentDTO){
            assessmentService.addAssessment((AssessmentDTO) progress);
        } else if(progress instanceof QuestionDTO){
            questionService.addQuestion((QuestionDTO) progress);
        }

        acknowledgment.acknowledge();
    }
}
