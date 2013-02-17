package pl.agh.enrollme.controller.preferencesmanagement;

import org.primefaces.event.*;
import org.primefaces.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleController.class);

    //Custom container model for the EnrollSchedule component
    private EnrollScheduleModel eventModel = new DefaultEnrollScheduleModel();

    //Custom event model for the EnrollSchedule component
    private EnrollScheduleEvent event = new DefaultEnrollScheduleEvent();

    //Schedule theme: as of today, unused
    private String theme;

    //Enroll data
    private EnrollConfiguration enrollConfiguration;

    private List<Subject> subjects;

    private List<Term> terms;

    private List<StudentPointsPerTerm> points;
    //Enroll data end

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

        int id = 0;

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

            //setting event's place
            event.setPlace(t.getRoom());

            Teacher teacher = t.getTeacher();
            String teacherString = teacher.getDegree() + " " + teacher.getFirstName().charAt(0) +
                    ". " + teacher.getSecondName();
            //setting event's teacher
            event.setTeacher(teacherString);

            final Subject subject = t.getSubject();

            //setting event's title to subjects' name
            event.setTitle(subject.getName());

            //setting event possibility
            event.setPossible(p.getPoints() != -1);

            //setting event importance as percent of total points available to this event
            event.setImportance((int) ((double) event.getPoints() / (double) enrollConfiguration.getPointsPerTerm() * 100));

            //setting event's color to that of event's subject
            event.setColor(subject.getColor()); //TODO: support setting colors in event model

            //setting event's type
            event.setType(t.getType()); //TODO: support setting event's type in event model

            //setting whether event is static or not
            event.setStatic(t.getCertain()); //TODO: support setting event as static (reacting to select event...)

            //setting whether to display points or not
            event.setShowPoints(t.getCertain()); //TODO: support setting whether points should be displayed or not

            //event's shouldn't be editable
            event.setEditable(false);

            //adding event to the container
            eventModel.addEvent(event);
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


    //Getters for Schedule attributes
    public boolean isShowWeekends() {
        return showWeekends;
    }

    public boolean isPeriodic() {
        return periodic;
    }

    public int getSlotMinutes() {
        return slotMinutes;
    }

    public int getFirstHour() {
        return firstHour;
    }

    public int getMinTime() {
        return minTime;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public String getInitialDate() {
        return initialDate;
    }

    public String getLeftHeaderTemplate() {
        return leftHeaderTemplate;
    }

    public String getCenterHeaderTemplate() {
        return centerHeaderTemplate;
    }

    public String getRightHeaderTemplate() {
        return rightHeaderTemplate;
    }
    //Getters for Schedule attributes end
}