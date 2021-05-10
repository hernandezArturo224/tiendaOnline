package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.models.Usuarios;
import tienda.arturo.hernandez.repository.UsuariosRepository;

@Service
public class UsuariosService {

	@Autowired
	private UsuariosRepository rep;
	
	/*public Usuarios getLogUser(String email) {
		
		Usuarios us = 
		
	}*/
	
	public Usuarios getUserbyId(int id) {
		Usuarios us = rep.findById(id);
		
		return us;
	}
	
	public Usuarios getUserByEmail(String email) {
		Usuarios us = rep.findByEmail(email);
		
		return us;
	}
	
	public Iterable getListaUsuarios() {
		return rep.findAll();
	}
	
	public void guardarUsuario(Usuarios user) {
		rep.save(user);
	}
	
}
