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

    //Expire time? (after this date it would be deleted from database... ?)

    public Enroll() {
        EnrollID = 0;
        name = "";
    }


    public Enroll(String name) {
        EnrollID = 0;
        this.name = name;
    }

    public void setEnrollID(Integer enrollID) {
        EnrollID = enrollID;
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
