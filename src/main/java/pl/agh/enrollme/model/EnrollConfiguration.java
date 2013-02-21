package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
@Table(name = "enrollconf1_")
public class EnrollConfiguration implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @Id
    @GeneratedValue
    private Integer ID;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Enroll enroll;

    private Integer pointsPerTerm;
    private Integer pointsPerSubject;
    private Integer minimumPointsPerSubject;
    private Integer additionalPoints;
    private Integer weekViewWidth;
    private Boolean periodic;

    public EnrollConfiguration() {
        this.enroll = null;
        this.pointsPerTerm = 0;
        this.pointsPerSubject = 0;
        this.additionalPoints = 0;
        this.minimumPointsPerSubject = 0;
        this.periodic = true;
        this.weekViewWidth = 1500;
    }

    public EnrollConfiguration(Enroll enroll, Integer pointsPerTerm, Integer pointsPerSubject,
                               Integer minimumPointsPerSubject, Integer additionalPoints) {
        this.enroll = enroll;
        this.pointsPerTerm = pointsPerTerm;
        this.pointsPerSubject = pointsPerSubject;
        this.minimumPointsPerSubject = minimumPointsPerSubject;
        this.additionalPoints = additionalPoints;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {

        return ID;
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

    public Enroll getEnroll() {
        return enroll;
    }

    public void setEnroll(Enroll enroll) {
        this.enroll = enroll;
    }

    public boolean getPeriodic() {
        return periodic;
    }

    public void setPeriodic(boolean periodic) {
        this.periodic = periodic;
    }

    public int getWeekViewWidth() {
        return weekViewWidth;
    }

    public void setWeekViewWidth(Integer weekViewWidth) {
        this.weekViewWidth = weekViewWidth;
    }
}
