package com.wiley.resultcontext.service;

import com.wiley.resultcontext.dto.AssessmentDTO;
import com.wiley.resultcontext.dto.PageResult;
import com.wiley.resultcontext.dto.PaginationDTO;
import com.wiley.resultcontext.entity.Assessment;
import com.wiley.resultcontext.repository.AssessmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AssessmentDTO addAssessment(AssessmentDTO assessmentDTO) {
        Assessment assessment = modelMapper.map(assessmentDTO, Assessment.class);
        assessment.setCreatedBy("admin-create");
        assessment.setLastModifiedBy("admin-create");
        return modelMapper.map(assessmentRepository.save(assessment), AssessmentDTO.class);
    }


    @Override
    public AssessmentDTO updateAssessment(AssessmentDTO assessmentDTO) {
        Assessment oldAssessment = assessmentRepository.findByAssessmentId(assessmentDTO.getAssessmentId());
        if (oldAssessment != null) {
            Assessment assessment = modelMapper.map(assessmentDTO, Assessment.class);
            assessment.setKey(oldAssessment.getKey());
            assessment.setCreatedBy(oldAssessment.getCreatedBy());
            assessment.setCreationDate(oldAssessment.getCreationDate());
            assessment.setVersionId(oldAssessment.getVersionId());
            assessment.setLastModifiedBy("admin-lastmodifiedby");

            return modelMapper.map(assessmentRepository.save(assessment), AssessmentDTO.class);
        }
        return modelMapper.map(assessmentRepository.save(modelMapper.map(assessmentDTO, Assessment.class)), AssessmentDTO.class);
    }

    @Override
    public PageResult<AssessmentDTO> getAssessments(String contextId, String resourceId, PaginationDTO paginationDTO) {
        List<Sort.Order> orders = getSortOrder(paginationDTO.getOrderByList());
        Pageable pageable;
        if (orders != null) {
            pageable = PageRequest.of(paginationDTO.getPage(),
                    paginationDTO.getSize(), Sort.by(orders));
        } else {
            pageable = PageRequest.of(paginationDTO.getPage(),
                    paginationDTO.getSize());
        }

        Page<Assessment> page = assessmentRepository.findByLearningContextIdAndResourceLinkId(
                contextId, resourceId, pageable);
        if (page.hasContent() && !page.getContent().isEmpty()) {
            List<AssessmentDTO> assessmentDtos = page.getContent().stream()
                    .map(assessment -> modelMapper.map(assessment, AssessmentDTO.class))
                    .collect(Collectors.toList());
            return new PageResult<AssessmentDTO>(assessmentDtos, page.getNumber(),
                    page.getTotalPages(), page.getTotalElements());
        }
        return null;
    }

    private List<Sort.Order> getSortOrder(String[] sortOrder) {
        if (sortOrder != null && sortOrder.length > 0) {
            List<Sort.Order> orders = new ArrayList<>();
            if (sortOrder.length == 1) {
                String sortField = sortOrder[0];
                orders.add(getOrder(sortField));
            } else {
                for (String sortItem : sortOrder) {
                    orders.add(getOrder(sortItem));
                }
            }
            return orders;
        }
        return null;
    }

    private Sort.Order getOrder(String sortField) {
        Sort.Direction direction = Sort.Direction.ASC;
        String field = sortField;
        if (sortField.contains("-")) {
            direction = Sort.Direction.DESC;
            field = sortField.substring(1, sortField.length());
        }
        return new Sort.Order(direction, field);
    }
}
