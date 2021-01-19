package com.wiley.resultcontext.controller;

import com.wiley.resultcontext.dto.MessageType;
import com.wiley.resultcontext.dto.ProgressMessage;
import com.wiley.resultcontext.dto.QuestionDTO;
import com.wiley.resultcontext.service.KafKaProducerService;
import com.wiley.resultcontext.service.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/questions/v1")
@Api(tags = "question service", value = "QuestionCommands")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private KafKaProducerService producerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new question")
    public ResponseEntity<QuestionDTO> createQuestion(@Valid @RequestBody QuestionDTO questionDTO) {


        producerService.sendMessage(questionDTO);

        return new ResponseEntity<>(questionDTO, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update an existing question")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDTO) {
        return new ResponseEntity<>(questionService.updateQuestion(questionDTO), HttpStatus.OK);
    }
}
