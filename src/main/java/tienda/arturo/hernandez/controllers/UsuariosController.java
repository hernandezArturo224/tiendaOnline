package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.services.UsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	@Autowired
	private UsuariosService serUsuarios;
	
	@GetMapping("")
	public String listarUsuarios(Model model) {
		model.addAttribute("usuarios",serUsuarios.getListaUsuarios());
		return "usuarios/listaUsuarios";
		
	}

}
