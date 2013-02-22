package pl.agh.enrollme.repository;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PersonDAO extends GenericDAO<Person> implements IPersonDAO {

    @PersistenceContext
    EntityManager em;

    public PersonDAO() {
        super(Person.class);
    }

    @Transactional
    @Override
    public Person findByUsername(String username) {
        final TypedQuery<Person> query = em.createQuery("Select p from Person p where p.username = :username",
                Person.class).setParameter("username", username);
        final List<Person> resultList = query.getResultList();

        if (resultList.size() > 1) {
           throw new IllegalStateException("User " + username + " is not unique in data source!");
        }

        if (resultList.isEmpty()) {
            return null;
        } else {
            return resultList.get(0);
        }
    }

    @Override
    @Transactional
    public Person getCurrentUser() {
        UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Person person = (Person)userDetails;
        return getByPK(person.getId());
    }

}
