package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tienda.arturo.hernandez.models.*;
import java.util.*;

import javax.servlet.http.HttpSession;

import tienda.arturo.hernandez.services.Detalles_pedidoService;
import tienda.arturo.hernandez.services.PedidosService;
import tienda.arturo.hernandez.services.ProductosService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PedidosService serPedidos;
	
	@Autowired
	private Detalles_pedidoService serDetalles;
	
	@Autowired
	private ProductosService serProductos;
	
	@GetMapping("")
	public String muestraPerfil() {
		
		return "perfil/perfil";
	}
	
	
	@GetMapping("/pedidos")
	public String misPedidos(HttpSession sesion,Model model) {
		
		Usuarios usuario = (Usuarios)sesion.getAttribute("user");
		
		List<Pedidos> pedidos = serPedidos.getPedidosUser(usuario.getId());
		
		model.addAttribute("pedidos",pedidos);
		
		return "perfil/pedidos";
	}
	
	@GetMapping("/detalles/{id}")
	public String verDetallesPedido(@PathVariable("id") int id,Model model) {
		List<Detalles_pedido> detalles = serDetalles.getDetallesFromPedido(id);
		List<Productos> productos = (List<Productos>)serProductos.getListaProductos();
		
		model.addAttribute("productos",productos);
		model.addAttribute("detalles",detalles);
		return "perfil/detallesPedido";
	}
	
	@GetMapping("/cancelar/{id}")
	public String cancelarPedido(@PathVariable("id") int id) {
		Pedidos pedido = serPedidos.getPedidoById(id);
		pedido.setEstado("cancelado");
		serPedidos.guardarPedido(pedido);
		
		return "redirect:/perfil/pedidos";
	}

}
