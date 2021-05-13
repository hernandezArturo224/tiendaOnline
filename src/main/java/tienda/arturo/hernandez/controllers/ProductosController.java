package tienda.arturo.hernandez.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tienda.arturo.hernandez.models.Categorias;
import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.services.CategoriasService;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.utilidades.StringUtilities;


@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private CategoriasService serCategorias;
	
	@GetMapping("")
	public String listarProductos(Model model) {
		model.addAttribute("productos",serProductos.getListaProductos());
		return "productos/listaProductos";
	}
	
	@GetMapping("/del/{id}")
	public String deleteProducto(@PathVariable("id") int id, Model model ) {
		serProductos.eliminarProducto(id);
		return "redirect:/productos";
	}
	
	@GetMapping("/edit/{id}")
	public String editProducto(@PathVariable("id") int id, Model model) {
		List<Categorias> categorias = (List)serCategorias.getListaCategorias();
		Productos producto = serProductos.getProductoFromId(id);
		model.addAttribute("categorias",categorias);
		model.addAttribute("productos",producto);
		return "productos/new";
	}
	
	@PostMapping("/edit/submit")
	public String editSubmit( @ModelAttribute Productos producto, @RequestParam String baja) {
		if(!baja.equals("")) {
			Timestamp fecha_baja = StringUtilities.getTimestampFromString(baja);
			producto.setFecha_baja(fecha_baja);
		}
		serProductos.guardarProducto(producto);
		return "redirect:/productos";
	}
	
	@GetMapping("/insertar")
	public String insertaProducto(Model model) {
		List<Categorias> categorias = (List)serCategorias.getListaCategorias();
		
		model.addAttribute("categorias",categorias);
		model.addAttribute("productos",new Productos());
		return "productos/new";
	}
	
	@PostMapping("/insertar/submit")
	public String insertaSubmit(@Valid @ModelAttribute Productos producto,BindingResult binding,Model model) {
		if(binding.hasErrors()) {
			List<Categorias> categorias = (List)serCategorias.getListaCategorias();
			
			model.addAttribute("categorias",categorias);
			model.addAttribute("productos",producto);
			return "productos/new";
		}else {
			serProductos.guardarProducto(producto);
			return "redirect:/productos";
		}
	}
	
}

