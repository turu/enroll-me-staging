package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.Subject;

/**
 * @author Michal Partyka
 */
public interface ISubjectDAO {
    void fillCurrentUserSubjectList(Subject[] subjects);
}
