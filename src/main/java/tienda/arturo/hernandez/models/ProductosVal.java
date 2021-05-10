package tienda.arturo.hernandez.models;

public class ProductosVal {
	
	private Productos producto;
	
	private double valoracionMedia;

	public ProductosVal(Productos producto, double valoracionMedia) {
		this.producto = producto;
		this.valoracionMedia = valoracionMedia;
	}
	
	

	public ProductosVal() {
		
	}



	@Override
	public String toString() {
		return "ProductosVal [producto=" + producto + ", valoracionMedia=" + valoracionMedia + "]";
	}



	public Productos getProducto() {
		return producto;
	}

	public void setProducto(Productos producto) {
		this.producto = producto;
	}

	public double getValoracionMedia() {
		return valoracionMedia;
	}

	public void setValoracionMedia(double valoracionMedia) {
		this.valoracionMedia = valoracionMedia;
	}
	
	

}
