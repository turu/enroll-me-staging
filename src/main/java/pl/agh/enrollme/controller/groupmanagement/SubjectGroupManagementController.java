package pl.agh.enrollme.controller.groupmanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Person;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.IPersonDAO;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rafał Cymerys
 */
public class SubjectGroupManagementController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectGroupManagementController.class);

    private Subject subject;

    private Group selectedGroup;

    private Person currentPerson;

    public SubjectGroupManagementController() {

    }

    public SubjectGroupManagementController(Subject subject, Person currentPerson) {
        this.subject = subject;
        this.currentPerson = currentPerson;
        this.selectedGroup = retrieveSelectedGroup();
        if (selectedGroup != null) {
            LOGGER.debug("Selected group for " + subject.getName() + " - " + selectedGroup.getName());
        }
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void setSelectedGroup(Group selectedGroup) {
        if (selectedGroup != null) {
            LOGGER.debug(subject.getName() + " - Setting selected group to " + selectedGroup.getName());
        } else {
            LOGGER.debug(subject.getName() + " - Deselecting selected group");
        }

        /* Exceeded capacity */
        if ((selectedGroup != null) &&
                (selectedGroup.getPersons().size() + 1 > selectedGroup.getSubject().getTeamsCapacity())) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Group " + selectedGroup.getName() + " is already full.", ""));
        } else {
            this.selectedGroup = selectedGroup;
        }
    }

    public Person getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(Person currentPerson) {
        this.currentPerson = currentPerson;
    }

    public String getSelectedGroupTeamString() {
        if (this.selectedGroup == null) {
            return "-";
        }

        StringBuilder result = new StringBuilder();

        for (Person person : selectedGroup.getPersons()) {
            result.append(person.getFirstName()).append(" ").append(person.getLastName()).append("\n");
        }

        return result.toString();
    }

    public List<Person> getSelectedGroupTeam() {
        List<Person> result = new ArrayList<>();

        if (this.selectedGroup != null) {
            result.addAll(selectedGroup.getPersons());
        }

        return result;
    }

    public boolean isOwnerOfSelectedGroup() {
        if (selectedGroup == null) {
            return false;
        }

        return selectedGroup.getOwner().equals(currentPerson);
    }

    private Group retrieveSelectedGroup() {
        for (Group g : currentPerson.getGroups()) {
            if (g.getSubject().equals(subject)) {
                return g;
            }
        }

        return null;
    }

    public void removePersonFromOwnedGroup(Person person) {
        if (selectedGroup == null) {
            throw new IllegalStateException("No group selected and owned");
        }

        if (person.equals(currentPerson)) {
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Cannot remove yourself from group you've created.\n Remove the group first.", ""));
            return;
        }

        LOGGER.debug("Removing person " + person.getFirstName() + " " + person.getLastName() + " from group" +
            selectedGroup.getName());

        Iterator<Person> iterator = selectedGroup.getPersons().iterator();
        while (iterator.hasNext()) {
            Person p = iterator.next();

            if (p.equals(person)) {
                p.getGroups().remove(selectedGroup);
                iterator.remove();

                return ;
            }
        }
    }
}
