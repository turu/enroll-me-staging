package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Person;

import java.util.List;

public interface IPersonDAO {

    void add(Person person);
    Person update(Person person);
    void removeByPK(Object id);
    void remove(Person person);
    Person getByPK(Object id);
    List<Person> getList();
    Person findByUsername(String username);

}
