package pl.agh.enrollme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class EnrollConfiguration implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;


    //TODO: it should be 1:1 relation with Enroll! or it should be merged (2nd solution is better probably)
    @Id
    private Integer enroll_ID;

    private Integer pointsPerTerm;
    private Integer pointsPerSubject;
    private Integer minimumPointsPerSubject;
    private Integer additionalPoints;

    public EnrollConfiguration() {
        this.enroll_ID = 0;
        this.pointsPerTerm = 0;
        this.pointsPerSubject = 0;
        this.additionalPoints = 0;
        this.minimumPointsPerSubject = 0;
    }

    public EnrollConfiguration(Integer enroll_ID, Integer pointsPerTerm, Integer pointsPerSubject,
                               Integer minimumPointsPerSubject, Integer additionalPoints) {
        this.enroll_ID = enroll_ID;
        this.pointsPerTerm = pointsPerTerm;
        this.pointsPerSubject = pointsPerSubject;
        this.minimumPointsPerSubject = minimumPointsPerSubject;
        this.additionalPoints = additionalPoints;
    }

    public void setPointsPerSubject(Integer pointsPerSubject) {
        this.pointsPerSubject = pointsPerSubject;
    }

    public void setMinimumPointsPerSubject(Integer minimumPointsPerSubject) {
        this.minimumPointsPerSubject = minimumPointsPerSubject;
    }

    public void setAdditionalPoints(Integer additionalPoints) {
        this.additionalPoints = additionalPoints;
    }

    public Integer getPointsPerSubject() {

        return pointsPerSubject;
    }

    public Integer getMinimumPointsPerSubject() {
        return minimumPointsPerSubject;
    }

    public Integer getAdditionalPoints() {
        return additionalPoints;
    }

    public Integer getPointsPerTerm() {
        return pointsPerTerm;
    }

    public void setPointsPerTerm(Integer pointsPerTerm) {
        this.pointsPerTerm = pointsPerTerm;
    }

    public Integer getEnroll_ID() {
        return enroll_ID;
    }

    public void setEnroll_ID(Integer id) {
        this.enroll_ID = id;
    }
}
