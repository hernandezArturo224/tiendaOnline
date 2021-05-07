package tienda.arturo.hernandez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios,Integer>{
	Usuarios findById(int id);
	Usuarios findByEmail(String email);
}
