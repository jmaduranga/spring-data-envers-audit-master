package com.wiley.resultcontext.repository;

import com.wiley.resultcontext.entity.Assessment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface AssessmentRepository extends CrudRepository<Assessment, Long> {
    Assessment findByAssessmentId(String assessmentId);
    Page<Assessment> findByLearningContextIdAndResourceLinkId(String learningContextId, String resourceLinkId, Pageable pageable);
}
