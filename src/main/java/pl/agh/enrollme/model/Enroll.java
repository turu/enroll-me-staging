package pl.agh.enrollme.model;

import pl.agh.enrollme.utils.EnrollmentMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Entity
public class Enroll implements Serializable {

    @Transient
    private static final long serialVersionUID = -577123547860923047L;

    @Id
    @GeneratedValue
    private Integer enrollID;

    private String name;
    private EnrollmentMode enrollmentMode;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private EnrollConfiguration enrollConfiguration;

    @OneToMany(mappedBy = "enroll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Person> persons;

    public EnrollConfiguration getEnrollConfiguration() {
        return enrollConfiguration;
    }

    public Enroll(String name, EnrollmentMode enrollmentMode, EnrollConfiguration enrollConfiguration, List<Subject> subjects) {
        this.enrollmentMode = enrollmentMode;
        this.enrollConfiguration = enrollConfiguration;
        this.subjects = subjects;
        enrollID = 0;
        this.name = name;
    }

    public void setEnrollmentMode(EnrollmentMode enrollmentMode) {
        this.enrollmentMode = enrollmentMode;
    }

    public EnrollmentMode getEnrollmentMode() {
        return enrollmentMode;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setEnrollID(Integer enrollID) {
        this.enrollID = enrollID;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setEnrollConfiguration(EnrollConfiguration enrollConfiguration) {
        this.enrollConfiguration = enrollConfiguration;
    }

    public Enroll() {
        enrollID = 0;
        name = "";
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getEnrollID() {

        return enrollID;
    }

    public String getName() {
        return name;
    }
}
