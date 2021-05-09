package tienda.arturo.hernandez.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.utilidades.StringUtilities;


@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	private ProductosService serProductos;
	
	@GetMapping("")
	public String listarProductos(Model model) {
		model.addAttribute("productos",serProductos.getListaProductos());
		return "productos/listaProductos";
	}
	
	@GetMapping("/insertar")
	public String insertaProducto(Model model) {
		model.addAttribute("productos",new Productos());
		return "productos/new";
	}
	
	@PostMapping("/insertar/submit")
	public String insertaSubmit(@ModelAttribute Productos producto, @RequestParam String baja) {
		Timestamp fecha_baja = StringUtilities.getTimestampFromString(baja);
		producto.setFecha_baja(fecha_baja);
		serProductos.guardarProducto(producto);
		return "redirect:/productos";
	}
	
}

