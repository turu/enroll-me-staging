package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class Enroll implements Serializable {

    @Transient
    private static final long serialVersionUID = -577123547860923047L;

    @Id
    @GeneratedValue
    private Integer EnrollID;

    private String name;

    @OneToOne(mappedBy = "enroll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EnrollConfiguration enrollConfiguration;

    public EnrollConfiguration getEnrollConfiguration() {
        return enrollConfiguration;
    }

    public Enroll(String name, EnrollConfiguration enrollConfiguration) {
        this.enrollConfiguration = enrollConfiguration;
        EnrollID = 0;
        this.name = name;
    }


    public void setEnrollID(Integer enrollID) {
        EnrollID = enrollID;
    }

    public void setEnrollConfiguration(EnrollConfiguration enrollConfiguration) {
        this.enrollConfiguration = enrollConfiguration;
    }

    public Enroll() {
        EnrollID = 0;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnrollID() {

        return EnrollID;
    }

    public String getName() {
        return name;
    }
}
