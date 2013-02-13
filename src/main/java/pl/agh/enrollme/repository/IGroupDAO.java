package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Subject;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Paweł
 * Date: 13.02.13
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupDAO {
    List<Group> getGroups(Subject subject);

    void tryToAddCurrentUserToGroup(Group group);
}
