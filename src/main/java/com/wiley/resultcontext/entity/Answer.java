package com.wiley.resultcontext.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer")
@Audited
@Getter
@Setter
@NoArgsConstructor
public class Answer extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long key;

    @Version
    private int version;

    @Column(name = "attempt_id")
    private String attemptId;

    @Column(name = "source")
    private String source;

    @Column(name = "doc_modification_datetime")
    private Date docModificationDatetime;

    @Column(name = "calculated_score")
    private double calculatedScore;

    @Column(name = "points_earned")
    private double pointsEarned;

    @Column(name = "points_possible")
    private double pointsPossible;

    @Column(name = "status")
    private String status;

    @Column(name = "time_spent")
    private long timeSpent;

    @Column(name = "question_key")
    private long questionKey;

    @ManyToOne
    @JoinColumn(name = "question_key", insertable = false, updatable = false)
    private Question question;
}
