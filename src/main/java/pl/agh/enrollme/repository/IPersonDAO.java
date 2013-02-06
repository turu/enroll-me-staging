package pl.agh.enrollme.repository;

import java.util.List;

import pl.agh.enrollme.model.Person;

public interface IPersonDAO {

	void addPerson(Person person);
	void removePerson(Integer id);
	List<Person> listPeople();
	
}