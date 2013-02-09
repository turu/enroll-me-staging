package pl.agh.enrollme.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;

/// // Please don't ask why I defined this parameter, believe, it's the only way to make it work.
@Entity //(name = "enrollment0_")
public class Event implements Serializable {

	@Transient
	private static final long serialVersionUID = -5777367229609230476L;

	@Id
	@GeneratedValue
	private Integer id;

	private String firstName;

	private String lastName;

	public Event() {
		this.id = 0;
		this.firstName = "";
		this.lastName = "";
	}

	public Event(int id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
