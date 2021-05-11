package tienda.arturo.hernandez.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.models.Provincias;
import tienda.arturo.hernandez.models.Usuarios;
import tienda.arturo.hernandez.services.UsuariosService;
import tienda.arturo.hernandez.utilidades.StringUtilities;
import tienda.arturo.hernandez.utilidades.Util;

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
	
	@GetMapping("/new")
	public String addUsuario(Model model) {
		Provincias[] prov = Util.getProvincias();
		
		
		model.addAttribute("provincias",prov);
		model.addAttribute("usuarios",new Usuarios());
		return "usuarios/new";
	}
	
	@PostMapping("/new/submit")
	public String addUsuarioSubmit(Model model, @ModelAttribute Usuarios user,HttpSession sesion) {
		user.setClave(StringUtilities.getEncryptedPassword(user.getClave()));
		serUsuarios.guardarUsuario(user);
		
		if(sesion.getAttribute("user") == null) {
			sesion.setAttribute("user", user);
		}
		
		return "redirect:/";
	}

}
