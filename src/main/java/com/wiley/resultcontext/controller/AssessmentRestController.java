package com.wiley.resultcontext.controller;

import com.wiley.resultcontext.dto.*;
import com.wiley.resultcontext.service.AssessmentService;
import com.wiley.resultcontext.service.KafKaProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

@RestController
@RequestMapping(value = "/api/assessments/v1")
@Api(tags = "Assessment service", value = "AssessmentCommands")
@Validated
@Slf4j
public class AssessmentRestController {
    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private KafKaProducerService producerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Create a new assessment")
    public ResponseEntity<AssessmentDTO> createVehicle(@Valid @RequestBody AssessmentDTO assessment) {

        log.info("Calling create assessment");
        log.error("test error log");

        producerService.sendMessage(assessment);

        return new ResponseEntity<>((AssessmentDTO) assessment, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update an existing assessment")
    public ResponseEntity<AssessmentDTO> updateVehicle(@RequestBody AssessmentDTO assessmentDTO) {
        return new ResponseEntity<>(assessmentService.updateAssessment(assessmentDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/assessments/context/{contextId}/resource/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "get assessments by context id and resource id")
    public ResponseEntity<PageResult<AssessmentDTO>> getAssessmentsByContextIdAndResourceId(@PathVariable(name = "contextId") String contextId,
                                                                                            @PathVariable(name = "resourceId") String resourceId,
                                                                                            @Min(value = 0, message = "Page should start from zero") @RequestParam(defaultValue = "0") int page,
                                                                                            @Min(value = 1, message = "size can not be less than 1") @RequestParam(defaultValue = "3") int size,
                                                                                            @RequestParam(defaultValue = "assessmentId") String sortOrder) {
        PaginationDTO paginationDTO = new PaginationDTO(page, size, sortOrder.split(","));
        PageResult<AssessmentDTO> pageResult = assessmentService.getAssessments(contextId, resourceId, paginationDTO);
        if (pageResult != null) {
            return new ResponseEntity<>(pageResult, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/version", produces = "application/json")
    public String getVersion() {

        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:version.json");
        try (Reader reader = new InputStreamReader(resource.getInputStream())) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
