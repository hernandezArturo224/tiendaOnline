package tienda.arturo.hernandez.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.Detalles_pedido;
import tienda.arturo.hernandez.models.Pedidos;
import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.models.ProductosPedido;
import tienda.arturo.hernandez.models.Usuarios;
import tienda.arturo.hernandez.services.Detalles_pedidoService;
import tienda.arturo.hernandez.services.PedidosService;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.utilidades.StringUtilities;
import tienda.arturo.hernandez.utilidades.UsoLogger;
import org.apache.log4j.Logger;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	
	Logger log = UsoLogger.getLogger(CarritoController.class);
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private PedidosService serPedidos;
	
	@Autowired
	private Detalles_pedidoService serDetalles;
	
	@GetMapping("")
	public String verCarrito(Model model,HttpSession sesion){
		if(sesion.getAttribute("user") == null) {
			log.error("No hay usuario registrado");
			return "/login";
		}else {
			List<ProductosPedido> carrito = (List<ProductosPedido>) sesion.getAttribute("carrito");
			log.info("Entrando al carrito");
			Double total = getTotal(carrito);
			sesion.setAttribute("total", total);
			return "carrito/carrito";
		}
		
	}
	
	@GetMapping("/putcarrito/{id}")
	public String putOnCarrito(@PathVariable("id") int id,HttpSession sesion) {
		Productos prod = serProductos.getProductoFromId(id);
		List<ProductosPedido> carrito = (List<ProductosPedido>) sesion.getAttribute("carrito");
		
			int indice = repetidoCarrito(prod,carrito);
			if(indice == -1) {
				ProductosPedido lineaPedido = new ProductosPedido(new Detalles_pedido(),prod );
				lineaPedido.getDetalles().setUnidades(1);
				lineaPedido.getDetalles().setPrecio_unidad((float)prod.getPrecio());
				lineaPedido.getDetalles().setProducto(prod.getId());
				lineaPedido.getDetalles().setTotal(prod.getPrecio());
				carrito.add(lineaPedido);
			}else {
				int unidades = carrito.get(indice).getDetalles().getUnidades()+1;
				carrito.get(indice).getDetalles().setUnidades(unidades);
				float precio = carrito.get(indice).getDetalles().getPrecio_unidad();
				carrito.get(indice).getDetalles().setTotal(unidades*precio);
			}
			
			log.info("Agregado al carrito");
		sesion.setAttribute("carrito", carrito);
		return "redirect:"+IndexController.redireccion;
	}
	
	@GetMapping("/comprar")
	public String confirmarCompra(HttpSession sesion,Model model) {
		List<ProductosPedido> carrito = (List<ProductosPedido>)sesion.getAttribute("carrito");
		Usuarios user = (Usuarios)sesion.getAttribute("user");
		
		Pedidos pedido = new Pedidos();
		pedido.setEstado("Sin confirmar");
		double total = getTotal(carrito);
		pedido.setTotal(total);
		pedido.setFecha(StringUtilities.getDefaultTimestamp());
		pedido.setUsuario(user.getId());
		
		sesion.setAttribute("pedido", pedido);
		
		log.info("Entrando a realizar el pedido");
		model.addAttribute("estado","Sin confirmar");
		model.addAttribute("pedido",pedido);
		return "carrito/confirmarCompra";
	}
	
	@PostMapping("/compraSubmit")
	public String compraSubmit(HttpSession sesion,@RequestParam String metodoPago, RedirectAttributes redirect) {
		Usuarios us = (Usuarios)sesion.getAttribute("user");
		
		if(us.getDireccion().equals("")) {
			redirect.addFlashAttribute("mensaje","Debes introducir la direccion para realizar la compra");
			log.error("No hay direccion establecida");
			return "redirect:/perfil/editar";
		}else {
			Pedidos pedido = (Pedidos)sesion.getAttribute("pedido");
			pedido.setMetodo_pago(metodoPago);
			pedido = serPedidos.guardarPedido(pedido);
			int idPedido = pedido.getId();
			
			List<ProductosPedido> carrito = (List<ProductosPedido>)sesion.getAttribute("carrito");
			log.info("Metiendo productos...");
			for(int i=0;i<carrito.size();i++) {
				Detalles_pedido detalles = carrito.get(i).getDetalles();
				detalles.setPedido(idPedido);
				detalles.setImpuesto(carrito.get(i).getProducto().getImpuesto());
				detalles.setProducto(carrito.get(i).getProducto().getId());
				
				int stock = carrito.get(i).getProducto().getStock() - detalles.getUnidades();
				
				if(stock < 0) {
					carrito.get(i).getProducto().setStock(0);
					pedido.setEstado("cancelado");
					serPedidos.guardarPedido(pedido);
				}else {
					carrito.get(i).getProducto().setStock(stock);
				}
				
				
				serProductos.guardarProducto(carrito.get(i).getProducto());
				serDetalles.guardarDetallesPedido(detalles);
			}//fin for
			carrito.clear();
			sesion.setAttribute("carrito", carrito);
			sesion.removeAttribute("pedido");
			
			return "redirect:/";
		}
		
	}
	
	@GetMapping("/vaciar")
	public String vaciarCarrito(HttpSession sesion) {
		List<ProductosPedido> carrito = (List<ProductosPedido>)sesion.getAttribute("carrito");
		carrito.clear();
		log.info("Carrito vaciado");
		sesion.setAttribute("carrito", carrito);
		return "redirect:/carrito";
	}
	
	public double getTotal(List<ProductosPedido> carrito) {
		double total=0;
		for(int i=0;i<carrito.size();i++) {
			total+=carrito.get(i).getDetalles().getTotal();
		}
		return total;
	}
	
	public int repetidoCarrito(Productos prod,List<ProductosPedido> carrito) {
		int devolver = -1;
		int i;
		for(i=0; i<carrito.size();i++) {
			if(prod.getId() == carrito.get(i).getProducto().getId()){
				devolver = i;
			}
		}
		return devolver;
	}

}
