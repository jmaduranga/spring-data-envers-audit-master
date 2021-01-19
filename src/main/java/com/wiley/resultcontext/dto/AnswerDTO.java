package com.wiley.resultcontext.dto;

import java.io.Serializable;
import java.util.Date;

public class AnswerDTO extends  Progress implements Serializable {
    private String attemptId;

    private String questionId;

    private String source;

    private Date docModificationDatetime;

    private double calculatedScore;

    private double pointsEarned;

    private double pointsPossible;

    private String status;

    private long timeSpent;
}
