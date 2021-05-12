package tienda.arturo.hernandez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Categorias;
import tienda.arturo.hernandez.repository.CategoriasRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriasRepository rep;
	
	public List<Categorias> getListaCategorias() {
		return rep.findAll();
	}
}
