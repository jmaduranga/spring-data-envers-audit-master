package com.wiley.resultcontext.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assessment")
@Audited
@Getter
@Setter
@NoArgsConstructor
public class Assessment extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long key;

    @Version
    private int versionId;

    @Column(name = "assessment_id")
    private String assessmentId;

    @Column(name = "source")
    private String source;

    @Column(name = "status")
    private String status;

    @Column(name = "time_duration")
    private int timeDuration;

    @Column(name = "time_accomadation")
    private int timeAccomadation;

    @Column(name = "time_unit")
    private boolean timeUnit;

    @Column(name = "sync_status")
    private String syncStatus;

    @Column(name = "learning_context_id")
    private String learningContextId;

    @Column(name = "resource_link_id")
    private String resourceLinkId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "start_datetime")
    private Date startDateTime;

    @Column(name = "end_datetime")
    private Date endDateTime;

    @Column(name = "doc_modification_datetime")
    private Date docModificationDateTime;

    @Column(name = "attempt_id")
    private long attemptId;

    @Column(name = "score")
    private double score;

    @Column(name = "assignment_id")
    private String assignmentId;

    @OneToMany(mappedBy = "assessment")
    private List<Question> questions;

}
