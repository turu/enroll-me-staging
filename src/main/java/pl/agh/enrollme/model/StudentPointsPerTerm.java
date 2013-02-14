package pl.agh.enrollme.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class StudentPointsPerTerm implements Serializable {

    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @Id
    @GeneratedValue
    private Integer Id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Term term;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person person;

    //-1 if NIEMOZLIWOŚĆ
    private Integer points;

    @Column(nullable = true)
    private String reason;

    public StudentPointsPerTerm() {
    }

    public StudentPointsPerTerm(Term term, Person person, Integer points, String reason) {
        this.term = term;
        this.person = person;
        this.points = points;
        this.reason = reason;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPoints() {
        return points;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Integer getId() {
        return Id;
    }

    public Term getTerm() {
        return term;
    }

    public Person getPerson() {
        return person;
    }
}