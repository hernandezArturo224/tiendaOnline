package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tienda.arturo.hernandez.models.*;
import java.util.*;

import javax.servlet.http.HttpSession;

import tienda.arturo.hernandez.services.PedidosService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PedidosService serPedidos;
	
	@GetMapping("")
	public String muestraPerfil() {
		
		return "perfil/perfil";
	}
	
	
	@GetMapping("/pedidos")
	public String misPedidos(HttpSession sesion,Model model) {
		
		Usuarios usuario = (Usuarios)sesion.getAttribute("user");
		
		List<Pedidos> pedidos = serPedidos.getPedidosUser(usuario.getId());
		
		model.addAttribute("pedidos",pedidos);
		
		return "perfil/pedidos";
	}

}
