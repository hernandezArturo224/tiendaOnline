package tienda.arturo.hernandez.models;

public class ProductosPedido {
	
	private Detalles_pedido detalles;
	
	private Productos producto;

	public ProductosPedido(Detalles_pedido detalles, Productos producto) {
		super();
		this.detalles = detalles;
		this.producto = producto;
	}

	public ProductosPedido() {
		super();
	}

	public Detalles_pedido getDetalles() {
		return detalles;
	}

	public void setDetalles(Detalles_pedido detalles) {
		this.detalles = detalles;
	}

	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	@Override
	public String toString() {
		return "ProductosPedido [detalles=" + detalles + ", producto=" + producto + "]";
	}
	
	

}
