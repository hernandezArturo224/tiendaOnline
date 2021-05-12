package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Configuracion;
import tienda.arturo.hernandez.repository.ConfiguracionRepository;

@Service
public class ConfiguracionService {

	@Autowired
	private ConfiguracionRepository rep;
	
	
	
	public Configuracion getValorClave(String clave) {
		return rep.findByClave(clave);
	}
	
	public void actualizarConfig(Configuracion config) {
		rep.save(config);
	}
	
}
