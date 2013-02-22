package pl.agh.enrollme.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.agh.enrollme.model.Group;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.repository.IGroupDAO;
import pl.agh.enrollme.repository.ITeacherDAO;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Rafał Cymerys
 */
@Service
@ViewScoped
public class GroupConverter implements Converter {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupConverter.class);

    @Autowired
    IGroupDAO groupDAO;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.equals("")) {
            return null;
        }

        Integer ID = Integer.parseInt(value);
        return groupDAO.getByPK(ID);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((Group)value).getId().toString();
    }
}
