package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Event;
import pl.agh.enrollme.model.Person;

import java.util.List;

public interface IEventDAO {

	void addEvent(Event event);
	void removeEvent(Integer id);
	List<Event> listEvents();
	
}