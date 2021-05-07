package tienda.arturo.hernandez.repository;

import tienda.arturo.hernandez.models.Opciones_menu;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Opciones_menu,Integer>{
	List<Opciones_menu> findByRol(int rol);
}
