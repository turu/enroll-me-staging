package pl.agh.enrollme.repository;

import pl.agh.enrollme.model.EnrollConfiguration;

/**
 * @author Michal Partyka
 */
public interface IConfigurationDAO {
    void addConfiguration(EnrollConfiguration configuration) throws Exception;
    EnrollConfiguration getConfigurationByID(Integer id);
}