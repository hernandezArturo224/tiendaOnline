package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Detalles_pedido;

public interface Detalles_pedidoRepository extends JpaRepository<Detalles_pedido,Integer>{

	List<Detalles_pedido> findByPedido(int pedido);
	
}
