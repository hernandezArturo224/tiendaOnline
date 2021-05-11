package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Pedidos;

public interface PedidosRepository extends JpaRepository<Pedidos,Integer>{

	List<Pedidos> findByUsuario(int Usuario);
	
	Pedidos findById(int id);
	
}
