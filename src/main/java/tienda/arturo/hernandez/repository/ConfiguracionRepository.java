package tienda.arturo.hernandez.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tienda.arturo.hernandez.models.Configuracion;

public interface ConfiguracionRepository extends JpaRepository<Configuracion,Integer>{

	Configuracion findByClave(String clave);
}
