package tienda.arturo.hernandez.controllers;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.Usuarios;
import tienda.arturo.hernandez.services.UsuariosService;
import tienda.arturo.hernandez.utilidades.StringUtilities;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	//private static final String MAIL="admin@admin.es";
	//private static final String PASS="1234";
	
	@Autowired
	private UsuariosService serUsuarios;
	
	@GetMapping("")
	public String loginIndex(Model model) {
		String mensaje = (String)model.asMap().get("mensaje");
		model.addAttribute("mensaje",mensaje);
		return "login";
	}
	//@ModelAttribute login
	
	@PostMapping("/log")
	public String getLogged(Model model,@ModelAttribute Usuarios us, RedirectAttributes redirect, HttpSession sesion) {
		System.out.println("Email: "+us.getEmail()+" Clave: "+us.getClave());
		
		Usuarios user = serUsuarios.getUserByEmail(us.getEmail());
		if(user != null) {
			
			if(StringUtilities.checkPassword(us.getClave(), user.getClave())) {
				sesion.setAttribute("user", user);
				return "redirect:/";
			}else {
				redirect.addFlashAttribute("mensaje","Error, clave no valida");
				return "redirect:/login"; //volvemos al login
			}
		}else {
			redirect.addFlashAttribute("mensaje","Error, email no valido");
			return "redirect:/login"; //volvemos al login
		}
	}
	
	@GetMapping("/logout")
	public String getLogout(HttpSession sesion) {
		sesion.invalidate();
		return "redirect:/";
	}
	
	/*@GetMapping("/log/{email}") //este metodo usarlo sin utilizar el html
	public String getLogged2(Model model, @PathVariable(required=true) String email, @RequestParam(defaultValue="1234") String clave) {
		if(email.equals(MAIL) && clave.equals(PASS)) {
			model.addAttribute("name","admin");
			return "index";
		}else {
			model.addAttribute("mensaje","Error de creedenciales");
			return "login";
		}	
	}*/

}
