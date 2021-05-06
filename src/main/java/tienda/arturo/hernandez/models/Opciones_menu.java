package tienda.arturo.hernandez.models;

import java.io.Serializable;

public class Opciones_menu implements Serializable{
	
	private int id;
	
	private int id_rol;
	
	private String opcion;
	
	private String ruta;
	
	public Opciones_menu() {
		
	}

	@Override
	public String toString() {
		return "Opciones_menu [id=" + id + ", id_rol=" + id_rol + ", opcion=" + opcion + ", ruta=" + ruta + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_rol() {
		return id_rol;
	}

	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Opciones_menu(int id, int id_rol, String opcion, String ruta) {
		super();
		this.id = id;
		this.id_rol = id_rol;
		this.opcion = opcion;
		this.ruta = ruta;
	}

}
