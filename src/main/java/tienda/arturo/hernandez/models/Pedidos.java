package tienda.arturo.hernandez.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pedidos")
public class Pedidos implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="id_usuario")
	private int usuario;
	
	private Timestamp fecha;
	
	private String metodo_pago;
	
	private String estado;
	
	private String num_factura;
	
	private double total;

	public Pedidos( int usuario, Timestamp fecha, String metodo_pago, String estado, String num_factura,
			double total) {
		super();
		this.usuario = usuario;
		this.fecha = fecha;
		this.metodo_pago = metodo_pago;
		this.estado = estado;
		this.num_factura = num_factura;
		this.total = total;
	}

	public Pedidos() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getMetodo_pago() {
		return metodo_pago;
	}

	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}

	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", usuario=" + usuario + ", fecha=" + fecha + ", metodo_pago=" + metodo_pago
				+ ", estado=" + estado + ", num_factura=" + num_factura + ", total=" + total + "]";
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getNum_factura() {
		return num_factura;
	}

	public void setNum_factura(String num_factura) {
		this.num_factura = num_factura;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
}
