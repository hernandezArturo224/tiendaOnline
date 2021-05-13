package tienda.arturo.hernandez.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Roles;

public interface RolesRepository extends JpaRepository<Roles,Integer>{
	
	List<Roles> findAll();

}
