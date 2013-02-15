package pl.agh.enrollme.converter;

import org.springframework.beans.factory.annotation.Autowired;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.repository.ITeacherDAO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="teacherConverter")
public class TeacherConverter implements Converter {

    @Autowired
    ITeacherDAO teacherDAO;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Integer ID = Integer.parseInt(value);
        return teacherDAO.getByPK(ID);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Teacher) value).getTeacherID().toString();
	}

}
