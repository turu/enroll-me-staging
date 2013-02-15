package pl.agh.enrollme.model;

import pl.agh.enrollme.utils.DayOfWeek;
import pl.agh.enrollme.utils.StupidDate;
import pl.agh.enrollme.utils.Week;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Michal Partyka
 */
@Entity
public class Term implements Serializable {
    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @EmbeddedId
    private TermPK termId;

    //Mapping from EmbeddedID!
    @MapsId("subject")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Subject subject;

    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StupidDate startTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private StupidDate endTime;

    private Week week;
    private Integer capacity;
    private String room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Teacher teacher;

    public Term() {
    }

    public Term(TermPK termId, DayOfWeek dayOfWeek, StupidDate startTime, StupidDate endTime,
                Week week, Integer capacity, String room, Teacher teacher) {
        this.termId = termId;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.week = week;
        this.capacity = capacity;
        this.room = room;
        this.teacher = teacher;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public TermPK getTermId() {
        return termId;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public StupidDate getStartTime() {
        return startTime;
    }

    public StupidDate getEndTime() {
        return endTime;
    }

    public Week getWeek() {
        return week;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getRoom() {
        return room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTermId(TermPK termId) {
        this.termId = termId;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(StupidDate startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(StupidDate endTime) {
        this.endTime = endTime;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
