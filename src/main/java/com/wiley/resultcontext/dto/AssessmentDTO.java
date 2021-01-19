package com.wiley.resultcontext.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "DTO for assessment")
public class AssessmentDTO extends Progress implements Serializable {

    @ApiModelProperty(notes = "assessment identifier", example = "abc")
    private String assessmentId;

    @ApiModelProperty(notes = "source who publish the assessment", example = "WAS")
    private String source;


    private String status;


    private int timeDuration;


    private int timeAccomadation;


    private boolean timeUnit;


    private String syncStatus;


    private String learningContextId;


    private String resourceLinkId;


    private String userId;


    private Date startDateTime;


    private Date endDateTime;


    private Date docModificationDateTime;


    private long attemptId;


    private double score;


    private String assignmentId;
}
