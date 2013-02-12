package pl.agh.enrollme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

@Entity
public class Event implements Serializable {

	@Transient
	private static final long serialVersionUID = -5777367229609230476L;

	@Id
	@GeneratedValue
	private Integer id;

	private String name;

    public Event() {
		this.id = 0;
		this.name = "";
	}

    public Event(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
