package tienda.arturo.hernandez.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="valoraciones")
public class Valoraciones implements Serializable{
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(name="id_producto")
	private int producto;
	
	@Column(name="id_usuario")
	private int usuario;
	
	private int valoracion;
	
	private String comentario;

	public Valoraciones(int id, int producto, int usuario, int valoracion, String comentario) {
		this.producto = producto;
		this.usuario = usuario;
		this.valoracion = valoracion;
		this.comentario = comentario;
	}

	public Valoraciones() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getUsuario() {
		return usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public int getValoracion() {
		return valoracion;
	}

	public void setValoracion(int valoracion) {
		this.valoracion = valoracion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Override
	public String toString() {
		return "Valoraciones [id=" + id + ", producto=" + producto + ", usuario=" + usuario + ", valoracion="
				+ valoracion + ", comentario=" + comentario + "]";
	}
	
	
	
	

}
