package tienda.arturo.hernandez.services;


import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tienda.arturo.hernandez.models.*;
import tienda.arturo.hernandez.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository rep;
	
	public Iterable getListaMenu() {
        return rep.findAll();
    }
	
	public Iterable getMenuFromRol(int id_rol) {
		List<Opciones_menu> lista1 = new ArrayList<Opciones_menu>();
		List<Opciones_menu> lista2 = new ArrayList<Opciones_menu>();
		if(id_rol == 1) {
			lista1 = rep.findByRol(1);
			lista1.addAll(rep.findByRol(2));
		}else if(id_rol == 2) {
			lista1 = rep.findByRol(id_rol);
		}else {
			lista1 = null;
		}
		
		return lista1;
	}

}
