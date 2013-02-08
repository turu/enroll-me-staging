package pl.agh.enrollme.model;

import org.hibernate.metamodel.source.binder.ManyToManyPluralAttributeElementSource;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


@Entity
public class Person implements Serializable {

    @Transient
    private static final long serialVersionUID = -5777367229609230476L;

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons", cascade = CascadeType.ALL)
    private List<Group> groups;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "persons", cascade = CascadeType.ALL)
    private List<Subject> subjects;

    public Person() {
        this.id = 0;
        this.firstName = "";
        this.lastName = "";
    }

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
    }

    public void addGroups(Group group) {
        groups.add(group);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
