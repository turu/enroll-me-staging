package pl.agh.enrollme.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class EnrollConfigurationqwe implements Serializable {

    @Transient
    private static final long serialVersionUID = -57564872296040476L;

    @Id
    @GeneratedValue
    private Integer enroll_ID;

    private Integer pointsPerTerm;

    public EnrollConfigurationqwe() {
        enroll_ID=1;
        pointsPerTerm=0;
    }

    public EnrollConfigurationqwe(Integer enroll_id, int pointsPerTerm) {
        enroll_ID = enroll_id;
        this.pointsPerTerm = pointsPerTerm;
    }

    public Integer getEnroll_ID() {
        return enroll_ID;
    }

    public Integer getPointsPerTerm() {
        return pointsPerTerm;
    }

    public void setEnroll_ID(Integer enroll_ID) {
        this.enroll_ID = enroll_ID;
    }

    public void setPointsPerTerm(Integer pointsPerTerm) {
        this.pointsPerTerm = pointsPerTerm;
    }
}
