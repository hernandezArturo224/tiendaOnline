package tienda.arturo.hernandez.services;

import java.util.List;

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
	
	public void eliminarProducto(int id) {
		rep.deleteById(id);
	}
	
	public Productos getProductoFromId(int id) {
		return rep.findById(id);
	}
	
	public List<Productos> busquedaProductos(String busqueda){
		return rep.buscarNombreDescripcion(busqueda);
	}

}
