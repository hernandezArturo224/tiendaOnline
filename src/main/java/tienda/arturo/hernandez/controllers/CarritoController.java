package tienda.arturo.hernandez.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.models.Detalles_pedido;
import tienda.arturo.hernandez.models.Productos;
import tienda.arturo.hernandez.models.ProductosPedido;
import tienda.arturo.hernandez.services.ProductosService;

@Controller
@RequestMapping("/carrito")
public class CarritoController {
	
	@Autowired
	private ProductosService serProductos;
	
	@GetMapping("")
	public String verCarrito(Model model,HttpSession sesion){
		if(sesion.getAttribute("user") == null) {
			return "/login";
		}else {
			List<ProductosPedido> carrito = (List<ProductosPedido>) sesion.getAttribute("carrito");
			Double total = getTotal(carrito);
			sesion.setAttribute("total", total);
			return "carrito";
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
		
			
	
		
		sesion.setAttribute("carrito", carrito);
		return "redirect:"+IndexController.redireccion;
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
