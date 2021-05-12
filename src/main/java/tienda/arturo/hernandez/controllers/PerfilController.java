package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tienda.arturo.hernandez.models.*;
import java.util.*;

import javax.servlet.http.HttpSession;

import tienda.arturo.hernandez.services.*;
import tienda.arturo.hernandez.utilidades.Util;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PedidosService serPedidos;
	
	@Autowired
	private Detalles_pedidoService serDetalles;
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private UsuariosService serUsuarios;
	
	@Autowired
	private ValoracionesService serValoraciones;
	
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
	
	@GetMapping("/editar")
	public String editarPerfil(HttpSession sesion,Model model) {
		String mensaje = (String)model.asMap().get("mensaje");
		
		Usuarios user = (Usuarios)sesion.getAttribute("user");
		Provincias[] prov = Util.getProvincias();
		model.addAttribute("mensaje",mensaje);
		model.addAttribute("provincias",prov);
		model.addAttribute("usuario",user);
		return "perfil/editar";
	}
	
	@PostMapping("/editar/submit")
	public String guardarCambios(@ModelAttribute Usuarios user,HttpSession sesion) {
		serUsuarios.guardarUsuario(user);
		sesion.setAttribute("user", user);
		return "redirect:/perfil";
	}
	
	@GetMapping("/valorar/{id}")
	public String valorarProducto(@PathVariable("id") int id,HttpSession sesion,Model model) {
		Productos producto = serProductos.getProductoFromId(id);
		Usuarios usuario = (Usuarios)sesion.getAttribute("user");
		
		List<Valoraciones> val = serValoraciones.getValoracionUsuario(usuario.getId(), producto.getId());
		
		Valoraciones valoracion = new Valoraciones();
		if(val.size() == 0) {
			valoracion.setProducto(producto.getId());
			valoracion.setUsuario(usuario.getId());
		}else {
			valoracion = val.get(0);
		}
		
		model.addAttribute("producto",producto);
		model.addAttribute("valoracion",valoracion);
		return "perfil/valorar";
	}
	
	@PostMapping("/valorar/submit")
	public String valorarProductoSubmit(@ModelAttribute Valoraciones valoracion) {
		serValoraciones.guardarValoracion(valoracion);
		
		return "redirect:/perfil/pedidos";
	}

}
