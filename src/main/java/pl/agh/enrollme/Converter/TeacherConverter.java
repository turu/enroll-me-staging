package pl.agh.enrollme.Converter;

import pl.agh.enrollme.model.Teacher;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="teacherConverter")
public class TeacherConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		return null;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        Teacher teacher = (Teacher) value;
        return
	}

}
