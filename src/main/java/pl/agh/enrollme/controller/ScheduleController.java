package pl.agh.enrollme.controller;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.*;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class ScheduleController implements Serializable {

    private static final long serialVersionUID = 1;

    public class RandomDate extends Random {

        public GregorianCalendar nextDate(GregorianCalendar lowerLimit, GregorianCalendar upperLimit) {
            final int lowerYear = lowerLimit.get(Calendar.YEAR);
            final int upperYear = upperLimit.get(Calendar.YEAR);
            final int lowerMonth = lowerLimit.get(Calendar.MONTH);
            final int upperMonth = upperLimit.get(Calendar.MONTH);
            final int lowerDay = lowerLimit.get(Calendar.DAY_OF_MONTH);
            final int upperDay = upperLimit.get(Calendar.DAY_OF_MONTH);

            final int year = nextInt(lowerYear, upperYear);
            final int month = nextInt(year == lowerYear ? lowerMonth : 0, year == upperYear ? upperMonth : 11);
            final GregorianCalendar result = new GregorianCalendar(year, month, 1);
            final int day = nextInt(year == lowerYear && month == lowerMonth ? lowerDay : 1,
                    year == upperYear && month == upperMonth ? upperDay : result.getActualMaximum(Calendar.MONTH));
            result.set(year, month, day);
            return result;
        }

        public String nextDateAsString(GregorianCalendar lowerLimit, GregorianCalendar upperLimit) {
            GregorianCalendar gc = nextDate(lowerLimit, upperLimit);
            return gc.get(Calendar.YEAR) + "-" + gc.get(Calendar.MONTH) + "-" + gc.get(Calendar.DAY_OF_MONTH);
        }

        public int nextInt(int start, int end) {
            return start + nextInt(end - start + 1);
        }

    }

    private ScheduleModel eventModel;

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    private ScheduleEvent event = new DefaultScheduleEvent();

    private String theme;

    public ScheduleController() {
        eventModel = new DefaultScheduleModel();
        GregorianCalendar gc = new GregorianCalendar(2013, 1, 11, 10, 15);

        Date begin = gc.getTime();
        gc.add(Calendar.HOUR_OF_DAY, 1);
        Date end = gc.getTime();
        eventModel.addEvent(new DefaultScheduleEvent("Champions League Match", begin, end));
    }

    public void addEvent(ActionEvent actionEvent) {
        if(event.getId() == null)
            eventModel.addEvent(event);
        else
            eventModel.updateEvent(event);

        event = new DefaultScheduleEvent();
    }

    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(DateSelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getSource(), (Date) selectEvent.getSource());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    //Getters and Setters
}