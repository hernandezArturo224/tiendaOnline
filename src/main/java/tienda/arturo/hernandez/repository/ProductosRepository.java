package tienda.arturo.hernandez.repository;

import tienda.arturo.hernandez.models.Productos;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductosRepository extends JpaRepository<Productos, Integer>{

}
