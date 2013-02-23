package pl.agh.enrollme.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Subject;
import pl.agh.enrollme.repository.ISubjectDAO;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * Author: Piotr Turek
 */
//@FacesConverter(value="subjectConverter")
@Service
@ViewScoped
public class SubjectConverter implements Converter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubjectConverter.class);

    @Autowired
    ISubjectDAO subjectDAO;

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.debug("Converting from " + value + " asObject...");
        Integer ID = Integer.parseInt(value);
        return subjectDAO.getByPK(ID);
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LOGGER.debug("Got for converting: " + value.toString());
        return ((Subject) value).getSubjectID().toString();
    }

}