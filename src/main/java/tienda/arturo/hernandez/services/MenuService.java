package tienda.arturo.hernandez.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tienda.arturo.hernandez.repository.MenuRepository;

@Service
public class MenuService {
	
	@Autowired
	private MenuRepository rep;
	
	public Iterable getListaMenu() {
        return rep.findAll();
    }

}
