package pl.agh.enrollme.controller.finalschedule;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultEnrollScheduleEvent;
import org.primefaces.model.DefaultEnrollScheduleModel;
import org.primefaces.model.EnrollScheduleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.agh.enrollme.controller.preferencesmanagement.ProgressRingController;
import pl.agh.enrollme.model.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

/**
 * Author: Piotr Turek
 */
public class FinalScheduleController implements Serializable {

    private static final long serialVersionUID = -740843017642008075L;

    private static final Logger LOGGER = LoggerFactory.getLogger(FinalScheduleController.class);

    //Custom container model for the EnrollSchedule component
    private EnrollScheduleModel eventModel = new DefaultEnrollScheduleModel();

    //Custom event model for the EnrollSchedule component; currently selected event
    private DefaultEnrollScheduleEvent event = new DefaultEnrollScheduleEvent();


    //Enroll data
    private EnrollConfiguration enrollConfiguration;

    private List<Subject> subjects;

    private List<Term> terms;

    private List<StudentPointsPerTerm> points;
    //Enroll data end

    //Schedule component attributes
    private boolean showWeekends = false;
    private boolean periodic = true;
    private int slotMinutes = 15;           //TODO: add necessary field to enroll so that slotMinutes can be adjusted
    private int firstHour = 8;
    private int minTime = 8;
    private int maxTime = 22;
    private Date initialDate = new Date();
    private String leftHeaderTemplate = "prev, next";
    private String centerHeaderTemplate = "title";
    private String rightHeaderTemplate = "month, agendaWeek, agendaDay";
    private String view = "agendaWeek";
    //Schedule theme: as of today, unused
    private String theme;


    public FinalScheduleController(EnrollConfiguration enrollConfiguration, List<Subject> subjects, List<Term> terms) {
        this.enrollConfiguration = enrollConfiguration;
        this.subjects = subjects;
        this.terms = terms;

        this.periodic = enrollConfiguration.getPeriodic();

        preprocessTerms();
    }


    public EnrollScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(EnrollScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public DefaultEnrollScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(DefaultEnrollScheduleEvent event) {
        this.event = event;
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



    //Enroll data getters and setters begin
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
    //Enroll data getters and setters end


    //Getters for Schedule attributes begin
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

    public Date getInitialDate() {
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

    public String getView() {
        return view;
    }

    public String getTheme() {
        return theme;
    }
    //Getters for Schedule attributes end


    private void preprocessTerms() {
        int minHour = Integer.MAX_VALUE;
        int maxHour = Integer.MIN_VALUE;
        GregorianCalendar minDate = new GregorianCalendar();
        minDate.setTimeInMillis(Long.MAX_VALUE);
        GregorianCalendar maxDate = new GregorianCalendar();
        maxDate.setTimeInMillis(Long.MIN_VALUE);

        //preprocess terms, computing scope of enrollment, creating events etc.
        for (Term t : terms) {
            LOGGER.debug("Processing of term: " + t);
            GregorianCalendar startTime = new GregorianCalendar();
            startTime.setTime(t.getStartTime());
            GregorianCalendar endTime = new GregorianCalendar();
            endTime.setTime(t.getEndTime());

            //if term starts on a Saturday or Sunday, set showWeekends to true
            if(startTime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || startTime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                showWeekends = true;
                LOGGER.debug("ShowWeekends set to true");
            }

            final int termStartHour = startTime.get(Calendar.HOUR_OF_DAY);
            final int termEndHour = endTime.get(Calendar.HOUR_OF_DAY);

            if (termStartHour < minHour) {
                minHour = termStartHour;
            }

            if (termEndHour > maxHour) {
                maxHour = termEndHour;
            }

            if (startTime.before(minDate)) {
                minDate = startTime;
            }

            if (endTime.after(maxDate)) {
                maxDate = endTime;
            }

            DefaultEnrollScheduleEvent event = new DefaultEnrollScheduleEvent();


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

            //setting event's start date
            event.setStartDate(t.getStartTime());

            //setting event's end date
            event.setEndDate(t.getEndTime());

            //setting event's color to that of event's subject
            event.setColor("#" + subject.getColor());

            //setting event's type
            event.setActivityType(t.getType());

            //setting whether event is static or not
            event.setInteractive(false);

            //setting whether to display points or not
            event.setShowPoints(false);

            //event's shouldn't be editable
            event.setEditable(false);

            //adding event to the model
            eventModel.addEvent(event);
            LOGGER.debug("New event: " + event + " added to eventModel");

        }
        LOGGER.debug("All terms processed");

        //update time fields, but only if there were some events added, otherwise use defaults
        if (terms.size() > 0) {
            this.minTime = minHour;
            this.firstHour = minHour;
            this.maxTime = maxHour != 23 ? maxHour + 1 : maxHour;
            this.initialDate = minDate.getTime();

            //infering right header contents and default view
            if (minDate.get(Calendar.YEAR) == maxDate.get(Calendar.YEAR)
                    && minDate.get(Calendar.DAY_OF_YEAR) == maxDate.get(Calendar.DAY_OF_YEAR)) {
                this.rightHeaderTemplate = "agendaDay";
                this.view = "agendaDay";
            } else if (minDate.get(Calendar.YEAR) == maxDate.get(Calendar.YEAR)
                    && minDate.get(Calendar.WEEK_OF_YEAR) == maxDate.get(Calendar.WEEK_OF_YEAR)) {
                this.rightHeaderTemplate = "agendaWeek, agendaDay";
                this.view = "agendaWeek";
            }
        }
    }

}
