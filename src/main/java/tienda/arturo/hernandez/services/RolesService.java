package tienda.arturo.hernandez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.repository.RolesRepository;
import tienda.arturo.hernandez.models.Roles;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rep;
	
	
	public List<Roles> getAllRoles(){
		return rep.findAll();
	}
	
}
