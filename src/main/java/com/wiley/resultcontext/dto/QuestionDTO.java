package com.wiley.resultcontext.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class QuestionDTO extends Progress implements Serializable {
    private String questionId;

    private String assessmentId;

    private String source;

    private Date docModificationDateTime;

    private double calculatedScore;

    private double pointsEarned;

    private double pointsPossible;

    private String status;

    private long timeSpent;
}
