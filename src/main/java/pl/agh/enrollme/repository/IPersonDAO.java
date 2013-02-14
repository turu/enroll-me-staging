package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Person;

import java.util.List;

public interface IPersonDAO {

    void add(Person person);
    void removeByPK(Object id);
    Person getByPK(Object id);
    List<Person> getList();
    Person findByUsername(String username);

}
