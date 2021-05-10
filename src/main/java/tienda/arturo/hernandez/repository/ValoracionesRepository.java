package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Valoraciones;

public interface ValoracionesRepository extends JpaRepository<Valoraciones,Integer>{
	List<Valoraciones> findByProducto(int producto);
	
}
