package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.repository.ProductosRepository;

@Service
public class ProductosService {
	
	@Autowired
	private ProductosRepository rep;
	
	public Iterable getListaProductos() {
        return rep.findAll();
    }
	
	public void guardarProducto(Productos producto) {
		rep.save(producto);
	}

}
