package pl.agh.enrollme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity(name="enrollment")
public class Enrollment implements Serializable {

    @Transient
    private static final long serialVersionUID = -1058276725038926524L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    public Enrollment() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
