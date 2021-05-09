package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.repository.CategoriasRepository;

@Service
public class CategoriasService {

	@Autowired
	private CategoriasRepository rep;
	
	public Iterable getListaCategorias() {
		return rep.findAll();
	}
}
