package tienda.arturo.hernandez.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="roles")
public class Roles implements Serializable{

	@Id
	@GeneratedValue
	private int id;
	
	private String rol;

	public Roles(String rol) {
		super();
		this.rol = rol;
	}

	public Roles() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	
	
}
