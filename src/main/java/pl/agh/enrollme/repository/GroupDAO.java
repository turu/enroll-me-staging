package pl.agh.enrollme.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.agh.enrollme.model.*;
import pl.agh.enrollme.utils.Color;
import pl.agh.enrollme.utils.DayOfWeek;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Michal Partyka
 */
@Repository
public class GroupDAO implements IGroupDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDAO.class.getName());

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public void addGroup(Subject subject) {

        List<Person> people = new ArrayList<Person>();
        /*Person guy1 = new Person();
        guy1.setUsername("eduardo");
        people.add(guy1);
        Person guy2 = new Person();
        guy2.setUsername("edmundo");
        people.add(guy2);*/

        // LOGGER.error(subject.getName() + "\n" + "\n");
        System.out.println("======================================== " + subject.getSubjectID().toString());
        //em.merge(subject);
        em.merge(new Group(people, subject));
    }

    @Override
    @Transactional
    public List<Group> getGroups(Subject subject) {

        /*List<Group> groups = new ArrayList<Group>();

        List<Person> people = new ArrayList<Person>();
        Person guy1 = new Person();
        guy1.setFirstName("Jasiu");
        people.add(guy1);
        Person guy2 = new Person();
        guy2.setFirstName("Jozek");
        people.add(guy2);

        groups.add(new Group(people, subject));

        List<Person> newPeople = new ArrayList<Person>();
        Person guy3 = new Person();
        guy3.setFirstName("Ryszard");
        newPeople.add(guy3);
        Person guy4 = new Person();
        guy4.setFirstName("Marian");
        newPeople.add(guy4);

        groups.add(new Group(newPeople, subject));      */

        //return groups;
        //return em.createQuery("FROM Group").getResultList();

        CriteriaQuery<Group> c = em.getCriteriaBuilder().createQuery(Group.class);
        /*Root<Event> from =*/ c.from(Group.class);
        //c.orderBy(em.getCriteriaBuilder().asc(from.get("name")));
        //Root<Event> from = c.from(Group.class);
        //c.where(c. from.get
        //return em.createQuery().get // .getResultList();
        return em
                .createQuery("from Group") //" where subject.subjectID = :id")
                //.setParameter("id", subject.getSubjectID())
                .getResultList();
    }

    @Override
    @Transactional
    public void tryToAddCurrentUserToGroup(Group group) {
        //System.out.println(group.getPersons().get(0).getFirstName());
        //LOGGER.error(group.getPersons().get(0).getFirstName() + "\n" + "\n");
    }

}
