package tienda.arturo.hernandez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Pedidos;
import tienda.arturo.hernandez.repository.PedidosRepository;

@Service
public class PedidosService {
	
	@Autowired
	private PedidosRepository rep;
	
	public Pedidos guardarPedido(Pedidos pedido) {
		return rep.save(pedido);
	}
	
	public List<Pedidos> getPedidosUser(int usuario){
		return rep.findByUsuario(usuario);
	}
	
	public Pedidos getPedidoById(int id) {
		return rep.findById(id);
	}

}
