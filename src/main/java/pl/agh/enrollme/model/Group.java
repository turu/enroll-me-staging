package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michal Partyka
 */
@Entity
@Table(name = "groups")
public class Group implements Serializable {

    @Transient
    private static final long serialVersionUID = -577736421430476L;

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Person> persons = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Subject subject;

    public Group() {
    }

    public Group(List<Person> persons, Subject subjects) {
        this.persons = persons;
        this.subject = subjects;
    }

    void addPerson(Person person) {
        persons.add(person);
    }

    public Integer getId() {
        return id;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
