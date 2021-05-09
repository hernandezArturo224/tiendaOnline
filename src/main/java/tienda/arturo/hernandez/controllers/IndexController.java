package tienda.arturo.hernandez.controllers;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.*;
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
	
	private List<Productos> productos;
	
	
	
	@GetMapping("")
	public String goIndex(Model model,HttpSession sesion) {
		System.out.println("Pasando por controlador");
		//Usuarios user = (Usuarios)model.asMap().get("user");
		productos = (List)serProductos.getListaProductos();
		model.addAttribute("productos",productos);
		
		Usuarios user = (Usuarios)sesion.getAttribute("user");
		if(user != null) {
			if(user.getId_rol() <= 2) {
				sesion.setAttribute("menu",serMenu.getMenuFromRol(user.getId_rol()));
			}
		}
		
		return "index";
	}
	
	@GetMapping("/precio")
	public String orderPrecio(Model model) {
		orderByPrecio();
		model.addAttribute("productos",productos);
		return "index";
	}
	
	
	public void orderByPrecio(){
		for(int i=0;i<productos.size()-1;i++) {
			if(productos.get(i).getPrecio() < productos.get(i+1).getPrecio()) {
				Productos aux = productos.get(i);
				productos.set(i, productos.get(i+1));
				productos.set(i+1, aux);
			}
		}
	}

}
