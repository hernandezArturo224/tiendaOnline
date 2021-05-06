package tienda.arturo.hernandez.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.services.ProductosService;


@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	private ProductosService serProductos;
	
	@GetMapping("")
	public String listarProductos(Model model) {
		model.addAttribute("productos",serProductos.getListaProductos());
		return "listaProductos";
	}
	
	
}

