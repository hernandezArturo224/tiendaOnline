package tienda.arturo.hernandez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.repository.ValoracionesRepository;
import tienda.arturo.hernandez.models.*;

@Service
public class ValoracionesService {
	
	@Autowired
	private ValoracionesRepository rep;
	
	public List<Valoraciones> getValoracionesFromProducto(int producto){
		return	rep.findByProducto(producto);
	}

}
