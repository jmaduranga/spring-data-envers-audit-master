package com.wiley.resultcontext.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "question")
@Audited
@Getter
@Setter
@NoArgsConstructor
public class Question extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long key;

    @Version
    private int version;

    @Column(name = "question_id")
    private String questionId;

    @Column(name = "source")
    private String source;

    @Column(name = "doc_modification_datetime")
    private Date docModificationDateTime;

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

    @Column(name = "assessment_key")
    private long assessmentKey;

    @ManyToOne
    @JoinColumn(name = "assessment_key", insertable = false, updatable = false, referencedColumnName = "key")
    private Assessment assessment;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

}
