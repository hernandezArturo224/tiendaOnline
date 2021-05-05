package tienda.arturo.hernandez.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.Usuarios;

@Controller
@RequestMapping("")
public class IndexController {
	
	@GetMapping("")
	public String goIndex(Model model) {
		System.out.println("Pasando por controlador");
		Usuarios user = (Usuarios)model.asMap().get("user");
		System.out.println(user);
		model.addAttribute("user",user);
		model.addAttribute("productos",ProductosController.getAllProducts());
		return "index";
	}

}
