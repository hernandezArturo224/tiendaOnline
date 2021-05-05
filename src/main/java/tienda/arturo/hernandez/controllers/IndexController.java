package tienda.arturo.hernandez.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("")
public class IndexController {
	
	@GetMapping("")
	public String goIndex(Model model, RedirectAttributes redirect) {
		System.out.println("Pasando por controlador");
		String name =(String)redirect.getAttribute("name");
		System.out.println(name);
		return "index";
	}

}
