package pl.agh.enrollme.model;

import pl.agh.enrollme.utils.Week;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Michal Partyka
 */
@Entity @IdClass(TermPK.class)
public class Term implements Serializable, Comparable<Term> {
    @Transient
    private static final long serialVersionUID = -5771235478609230476L;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    Subject subject;

    @Id
    private Integer termPerSubjectID;

    private Date startTime;
    private Date endTime;

    private Week week;
    private Integer capacity;
    private String room;
    private String type;        //type of activity: f.e lecture, lab, ex etc
    private Boolean certain;    //if true, an event is not part of the ongoing enrollment and cannot be assigned points

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Teacher teacher;

    public Term() {
    }

    public Term(Subject subject, Date startTime, Date endTime, Week week, Integer capacity,
                String room, Teacher teacher, String type, Boolean certain) {
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.week = week;
        this.capacity = capacity;
        this.room = room;
        this.teacher = teacher;
        this.type = type;
        this.certain = certain;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getStartTimeAsString() {
        return getTimeAsString(startTime);
    }

    public String getEndTimeAsString() {
        return getTimeAsString(endTime);
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

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getCertain() {
        return certain;
    }

    public void setCertain(Boolean certain) {
        this.certain = certain;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Integer getTermPerSubjectID() {
        return termPerSubjectID;
    }

    public void setTermPerSubjectID(Integer termPerSubjectID) {
        this.termPerSubjectID = termPerSubjectID;
    }

    @Override
    public String toString() {
        return "Term{" +
                "subject=" + subject +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", week=" + week +
                ", capacity=" + capacity +
                ", room='" + room + '\'' +
                ", type='" + type + '\'' +
                ", certain=" + certain +
                ", teacher=" + teacher +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Term)) return false;

        Term term = (Term) o;

        if (!subject.equals(term.subject)) return false;
        if (!termPerSubjectID.equals(term.termPerSubjectID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject.hashCode();
        result = 31 * result + termPerSubjectID.hashCode();
        return result;
    }

    @Override
    public int compareTo(Term o) {
        return termPerSubjectID.compareTo(o.getTermPerSubjectID());
    }

    private String getTimeAsString(Date time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int h = cal.get(Calendar.HOUR);
        int m = cal.get(Calendar.MINUTE);
        return String.format("%d:%02d", h, m);
    }
}
