package pl.agh.enrollme.model;

import com.sun.faces.spi.SerializationProvider;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class Teacher implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @Id
    @GeneratedValue
    private Integer TeacherID;

    private String degree;
    private String firstname;
    private String secondname;
    private String room;

    public Teacher() {
    }

    public Teacher(String degree, String firstname, String secondname, String room) {
        this.degree = degree;
        this.firstname = firstname;
        this.secondname = secondname;
        this.room = room;
    }

    public void setTeacherID(Integer teacherID) {
        TeacherID = teacherID;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getTeacherID() {
        return TeacherID;
    }

    public String getDegree() {
        return degree;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getRoom() {
        return room;
    }
}