package tienda.arturo.hernandez.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="detalles_pedido")
public class Detalles_pedido implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="id_pedido")
	private int pedido;
	
	@Column(name="id_producto")
	private int producto;
	
	private float precio_unidad;
	
	private int unidades;
	
	private float impuesto;
	
	private double total;

	public Detalles_pedido(int pedido, int producto, float precio_unidad, int unidades, float impuesto, double total) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.precio_unidad = precio_unidad;
		this.unidades = unidades;
		this.impuesto = impuesto;
		this.total = total;
	}

	public Detalles_pedido() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPedido() {
		return pedido;
	}

	public void setPedido(int pedido) {
		this.pedido = pedido;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public float getPrecio_unidad() {
		return precio_unidad;
	}

	public void setPrecio_unidad(float precio_unidad) {
		this.precio_unidad = precio_unidad;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public float getImpuesto() {
		return impuesto;
	}

	public void setImpuesto(float impuesto) {
		this.impuesto = impuesto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Detalles_pedido [id=" + id + ", pedido=" + pedido + ", producto=" + producto + ", precio_unidad="
				+ precio_unidad + ", unidades=" + unidades + ", impuesto=" + impuesto + ", total=" + total + "]";
	}
	
	

}
