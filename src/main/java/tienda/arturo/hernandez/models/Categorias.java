package tienda.arturo.hernandez.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="categorias")
public class Categorias implements Serializable{
	
	
	@Id
	@GeneratedValue
	private int id;
	
	private String nombre;
	
	private String descripcion;

	public Categorias(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Categorias() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Categorias [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
	}
	
	
	

}
