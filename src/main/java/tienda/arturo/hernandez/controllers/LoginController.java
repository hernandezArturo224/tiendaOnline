package tienda.arturo.hernandez.controllers;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.Usuarios;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private static final String MAIL="admin@admin.es";
	private static final String PASS="1234";
	
	@GetMapping("")
	public String loginIndex() {
		return "login";
	}
	//@ModelAttribute login
	
	@PostMapping("/log")
	public String getLogged(Model model,@ModelAttribute Usuarios us, RedirectAttributes redirect) {
		System.out.println("Email: "+us.getEmail()+" Clave: "+us.getClave());
		
		if(us.getEmail().equals(MAIL) && us.getClave().equals(PASS)) {
			//model.addAttribute("name","admin");
			redirect.addAttribute("name","admin");
			return "redirect:/"; //volvemos al controlador de index
		}else {
			model.addAttribute("mensaje","Error de creedenciales");
			return "redirect:/login"; //volvemos al login
		}	
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
