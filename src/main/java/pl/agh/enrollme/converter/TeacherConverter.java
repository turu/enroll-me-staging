package pl.agh.enrollme.converter;

import org.springframework.beans.factory.annotation.Autowired;
import pl.agh.enrollme.model.Teacher;
import pl.agh.enrollme.repository.ITeacherDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@FacesConverter(value="teacherConverter")
@ManagedBean
public class TeacherConverter implements Converter {

    @Autowired
    ITeacherDAO teacherDAO;

    @PersistenceContext
    EntityManager em;

	public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("[partyks DEBUG] Get for parsing:" + value);
        Integer ID = Integer.parseInt(value);
        System.out.println("[partyks DEBUG] after parsing:" + ID);
        if(em == null) {
            throw new UnsupportedOperationException("KUPA3");
        }
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
