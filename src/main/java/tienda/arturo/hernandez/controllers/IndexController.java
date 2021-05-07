package tienda.arturo.hernandez.controllers;

import javax.servlet.http.HttpSession;

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
	public String goIndex(Model model,HttpSession sesion) {
		System.out.println("Pasando por controlador");
		//Usuarios user = (Usuarios)model.asMap().get("user");
		model.addAttribute("productos",serProductos.getListaProductos());
		
		Usuarios user = (Usuarios)sesion.getAttribute("user");
		if(user != null) {
			if(user.getId_rol() <= 2) {
				model.addAttribute("menu",serMenu.getMenuFromRol(user.getId_rol()));
			}
		}
		
		return "index";
	}

}
