package pl.agh.enrollme.converter;

import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.repository.ITeacherDAO;
import pl.agh.enrollme.repository.TeacherDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

//@FacesConverter(value="teacherConverter")
@ManagedBean
public class TeacherConverter implements Converter {

    ITeacherDAO teacherDAO = new TeacherDAO();

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("[partyks DEBUG] Get for parsing:" + value);
        Integer ID = Integer.parseInt(value);
        System.out.println("[partyks DEBUG] after parsing:" + ID);
        if(teacherDAO == null) {
            throw new UnsupportedOperationException("KUPA");
        }
        Teacher aaa = teacherDAO.getByPK(117);

//        return teacherDAO.getByPK(ID);
        return teacherDAO.getByPK(117);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("[partyks DEBUG] returned as: " + ((Teacher) value).getTeacherID().toString());
        return ((Teacher) value).getTeacherID().toString();
	}

}
