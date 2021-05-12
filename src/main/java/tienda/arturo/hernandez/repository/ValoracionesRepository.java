package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tienda.arturo.hernandez.models.Valoraciones;

public interface ValoracionesRepository extends JpaRepository<Valoraciones,Integer>{
	List<Valoraciones> findByProducto(int producto);
	
	
	@Query("select v from Valoraciones v where v.usuario = ?1 and v.producto = ?2")
	List<Valoraciones> buscarValoracionUsuario(int usuario,int producto);
	
}
