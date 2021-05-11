package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Detalles_pedido;
import tienda.arturo.hernandez.repository.Detalles_pedidoRepository;

@Service
public class Detalles_pedidoService {

	@Autowired
	private Detalles_pedidoRepository rep;
	
	public void guardarDetallesPedido(Detalles_pedido detalles) {
		rep.save(detalles);
	}
}
