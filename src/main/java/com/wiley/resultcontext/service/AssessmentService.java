package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.AssessmentDTO;
import com.wiley.resultcontext.dto.PageResult;
import com.wiley.resultcontext.dto.PaginationDTO;

public interface AssessmentService {
    AssessmentDTO addAssessment(AssessmentDTO assessmentDTO);

    AssessmentDTO updateAssessment(AssessmentDTO assessmentDTO);

    PageResult<AssessmentDTO> getAssessments(String contextId, String resourceId, PaginationDTO paginationDTO);
}
