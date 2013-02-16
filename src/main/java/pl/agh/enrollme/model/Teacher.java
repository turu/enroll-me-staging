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
public class Teacher implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @Id
    @GeneratedValue
    private Integer TeacherID=0;

    private String degree;
    private String firstName;
    private String secondName;
    private String room;

    //For a while:
    @Override
    public String toString() {
        return "Teacher{" +
                "TeacherID=" + TeacherID +
                ", degree='" + degree + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", room='" + room + '\'' +
                '}';
    }

    public Teacher() {
    }

    public Teacher(String degree, String firstName, String secondName, String room) {
        this.degree = degree;
        this.firstName = firstName;
        this.secondName = secondName;
        this.room = room;
    }

    public void setTeacherID(Integer teacherID) {
        TeacherID = teacherID;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public void setSecondName(String secondname) {
        this.secondName = secondname;
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

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;

        Teacher teacher = (Teacher) o;

        if (!TeacherID.equals(teacher.TeacherID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return TeacherID.hashCode();
    }
}