package pl.agh.enrollme.webflow.services;

import pl.agh.enrollme.webflow.model.EnrollConfiguration;

import java.util.Map;

/**
 * @author Michal Partyka
 */
public interface IConfigurationDAO {
    void addConfiguration(EnrollConfiguration configuration);
    Map<String, Integer> getConfigurationByID(Integer id);
}
