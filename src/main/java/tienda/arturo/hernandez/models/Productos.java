package tienda.arturo.hernandez.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class Productos implements Serializable{
	
	private int id;
	
	private int id_categoria;
	
	private String nombre;
	
	private String descripcion;
	
	private double precio;
	
	private int stock;
	
	private Timestamp fecha_alta;
	
	private Timestamp fecha_baja;
	
	private float impuesto;
	
	public Productos() {
		
	}

	public Productos(int id_categoria, String nombre, String descripcion, double precio, int stock,
			Timestamp fecha_baja, float impuesto) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.fecha_baja = fecha_baja;
		this.impuesto = impuesto;
	}

	@Override
	public String toString() {
		return "Productos [id=" + id + ", id_categoria=" + id_categoria + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", precio=" + precio + ", stock=" + stock + ", fecha_alta=" + fecha_alta
				+ ", fecha_baja=" + fecha_baja + ", impuesto=" + impuesto + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public Timestamp getFecha_alta() {
		return fecha_alta;
	}

	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}

	public Timestamp getFecha_baja() {
		return fecha_baja;
	}

	public void setFecha_baja(Timestamp fecha_baja) {
		this.fecha_baja = fecha_baja;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

}
