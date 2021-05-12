package tienda.arturo.hernandez.repository;

import tienda.arturo.hernandez.models.Productos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProductosRepository extends JpaRepository<Productos, Integer>{
	Productos findById(int id);

	@Query("select p from Productos p where p.nombre like %?1% or p.descripcion like %?1%")
	List<Productos> buscarNombreDescripcion(String busqueda);
	
	
	@Query("select p from Productos p where p.nombre like %?1% or p.descripcion like %?1% and p.precio = ?2")
	List<Productos> buscarNombreDescripcionPrecio(String busqueda, double precio);

	
	@Query("select p from Productos p where p.precio = ?1")
	List<Productos> buscarNombreDescripcionPrecio(double precio);
	
	
	
	@Query("select p from Productos p where (p.nombre like %?1% or p.descripcion like %?1%) and p.id_categoria = ?2")
	List<Productos> buscarNombreDescripcion(String busqueda,int cate);
	
	
	@Query("select p from Productos p where (p.nombre like %?1% or p.descripcion like %?1%) and p.precio = ?2 and p.id_categoria = ?3")
	List<Productos> buscarNombreDescripcionPrecio(String busqueda, double precio,int cate);

	
	@Query("select p from Productos p where p.precio = ?1 and p.id_categoria = ?2")
	List<Productos> buscarNombreDescripcionPrecio(double precio,int cate);
}
