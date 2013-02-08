package pl.agh.enrollme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Michal Partyka
 */
@Entity
public class Enroll {

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
