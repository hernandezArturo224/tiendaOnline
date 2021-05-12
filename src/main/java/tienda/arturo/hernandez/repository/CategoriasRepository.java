package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Integer>{
	List<Categorias> findAll();
}
