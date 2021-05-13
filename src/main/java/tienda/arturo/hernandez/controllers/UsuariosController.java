package tienda.arturo.hernandez.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		
		String mensaje = (String)model.asMap().get("mensaje");
		
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("provincias",prov);
		model.addAttribute("usuarios",new Usuarios());
		return "usuarios/new";
	}
	
	@PostMapping("/new/submit")
	public String addUsuarioSubmit(Model model,@Valid @ModelAttribute Usuarios user,BindingResult binding,RedirectAttributes redirect,HttpSession sesion) {
		Usuarios repe = serUsuarios.getUserByEmail(user.getEmail());
			
			if(binding.hasErrors()) {
				Provincias[] prov = Util.getProvincias();
				model.addAttribute("provincias",prov);
				model.addAttribute("usuarios",user);
				return "usuarios/new";
			}else {
				
				if(repe == null) {
					user.setClave(StringUtilities.getEncryptedPassword(user.getClave()));
					serUsuarios.guardarUsuario(user);
					
					if(sesion.getAttribute("user") == null) {
						sesion.setAttribute("user", user);
					}
					
					return "redirect:/";
				}else {
					redirect.addFlashAttribute("mensaje","Email: "+user.getEmail()+" está en uso");
					return "redirect:/usuarios/new";
				}	
			}
		
	}

}
