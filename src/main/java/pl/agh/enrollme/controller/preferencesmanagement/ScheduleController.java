package pl.agh.enrollme.controller.preferencesmanagement;

import org.primefaces.event.*;
import org.primefaces.model.*;
import pl.agh.enrollme.model.EnrollConfiguration;
import pl.agh.enrollme.model.StudentPointsPerTerm;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.model.Term;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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


    public ScheduleController(EnrollConfiguration enrollConfiguration, List<Subject> subjects, List<Term> terms,
                              List<StudentPointsPerTerm> points) {
        this.enrollConfiguration = enrollConfiguration;
        this.subjects = subjects;
        this.terms = terms;
        this.points = points;
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