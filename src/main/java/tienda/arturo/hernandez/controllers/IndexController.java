package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.Usuarios;
import tienda.arturo.hernandez.services.MenuService;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.services.UsuariosService;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private UsuariosService serUsuarios;
	
	@Autowired
	private MenuService serMenu;
	
	
	
	@GetMapping("")
	public String goIndex(Model model) {
		System.out.println("Pasando por controlador");
		//Usuarios user = (Usuarios)model.asMap().get("user");
		Usuarios us = serUsuarios.getUserbyId(2);
		System.out.println(us);
		//model.addAttribute("user",user);
		model.addAttribute("productos",serProductos.getListaProductos());
		model.addAttribute("menu",serMenu.getListaMenu());
		return "index";
	}

}
