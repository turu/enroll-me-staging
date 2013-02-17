package pl.agh.enrollme.controller.preferencesmanagement;

import org.primefaces.event.*;
import org.primefaces.model.*;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.utils.DayOfWeek;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

/**
 * This class is used as a schedule controller in preferences-management view
 * Author: Piotr Turek
 */
public class ScheduleController implements Serializable {

    private static final long serialVersionUID = -740843017652008075L;

    private EnrollScheduleModel eventModel = new DefaultEnrollScheduleModel();

    private EnrollScheduleEvent event = new DefaultEnrollScheduleEvent();

    private String theme;

    private EnrollConfiguration enrollConfiguration;

    private List<Subject> subjects;

    private List<Term> terms;

    private List<StudentPointsPerTerm> points;

    //Mapping from Term to StudentPointsPerTerm
    private Map<Term, StudentPointsPerTerm> termToPointsMap = new HashMap<>();

    //Mapping from Event to Term
    private Map<DefaultEnrollScheduleEvent, Term> eventToTermMap = new HashMap<>();

    //Schedule component attributes
    private boolean showWeekends = false;
    private boolean periodic = true;
    private int slotMinutes = 15;
    private int firstHour = 8;
    private int minTime = 8;
    private int maxTime = 22;
    private String initialDate = "";
    private String leftHeaderTemplate = "";
    private String centerHeaderTemplate = "";
    private String rightHeaderTemplate = "";


    public ScheduleController(EnrollConfiguration enrollConfiguration, List<Subject> subjects, List<Term> terms,
                              List<StudentPointsPerTerm> points) {
        this.enrollConfiguration = enrollConfiguration;
        this.subjects = subjects;
        this.terms = terms;
        this.points = points;

        preprocessTerms();
    }

    private void preprocessTerms() {
        //create mapping: Term -> StudentPointsPerTerm to allow for efficient access to data
        for (StudentPointsPerTerm p : points) {
            termToPointsMap.put(p.getTerm(), p);
        }

        //preprocess terms, computing scope of enrollment, creating events etc.
        for (Term t : terms) {
            if (t.getDayOfWeek() == DayOfWeek.SATURDAY || t.getDayOfWeek() == DayOfWeek.SUNDAY) {
                showWeekends = true;
            }
            //TODO: how is t.getStart|EndTime() represented ??
            //TODO: infer firstHour, minTime, maxTime, initialDate, slotMinutes from starting and ending times of terms
            //TODO: compute contents of left, center, right headers based on the above data
            StudentPointsPerTerm p = termToPointsMap.get(t);
            DefaultEnrollScheduleEvent event = new DefaultEnrollScheduleEvent();
            eventToTermMap.put(event, t);

            //setting event's points
            if (p.getPoints() == -1) {
                event.setPoints(0);
            } else {
                event.setPoints(p.getPoints());
            }

            event.setPlace(t.getRoom());

            Teacher teacher = t.getTeacher();
            String teacherString = teacher.getDegree() + " " + teacher.getFirstName().charAt(0) +
                    ". " + teacher.getSecondName();
            event.setTeacher(teacherString);

            event.setTitle(t.getSubject().getName());
            event.setPossible(p.getPoints() == -1 ? false : true);
            //event.setImportance(event.getPoints() / );
        }
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public EnrollScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EnrollScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public EnrollScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(EnrollScheduleEvent event) {
        this.event = event;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (EnrollScheduleEvent) selectEvent.getObject();
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public EnrollConfiguration getEnrollConfiguration() {
        return enrollConfiguration;
    }

    public void setEnrollConfiguration(EnrollConfiguration enrollConfiguration) {
        this.enrollConfiguration = enrollConfiguration;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<Term> getTerms() {
        return terms;
    }

    public void setTerms(List<Term> terms) {
        this.terms = terms;
    }

    public List<StudentPointsPerTerm> getPoints() {
        return points;
    }

    public void setPoints(List<StudentPointsPerTerm> points) {
        this.points = points;
    }


}