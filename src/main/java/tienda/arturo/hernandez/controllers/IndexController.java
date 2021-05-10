package tienda.arturo.hernandez.controllers;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tienda.arturo.hernandez.models.*;
import tienda.arturo.hernandez.services.MenuService;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.services.UsuariosService;
import tienda.arturo.hernandez.services.ValoracionesService;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private ValoracionesService serValoraciones;
	
	private ArrayList<ProductosVal> productos =new ArrayList<ProductosVal>();
	private static boolean precio=true;
	private static boolean valoracion=true;
	protected static boolean carritoBool=true;
	private String redireccion="/";
	
	
	private void iniciaCarrito(HttpSession sesion) {
		ArrayList<Productos> carrito = new ArrayList<Productos>();
		sesion.setAttribute("carrito",carrito);
		carritoBool=false;
	}
	
	
	@GetMapping("")
	public String goIndex(Model model,HttpSession sesion) {
		System.out.println("Pasando por controlador");
		//Usuarios user = (Usuarios)model.asMap().get("user");
		if(carritoBool) {
			iniciaCarrito(sesion);
		}
		redireccion="/";
		List<Productos> prod = (List<Productos>)serProductos.getListaProductos();
		rellenaProductos(prod);
		model.addAttribute("productos",productos);
		
		return "index";
	}
	
	public void rellenaProductos(List<Productos> prod) {
		productos.clear();
		for(int i=0;i<prod.size();i++) {
			Productos product = prod.get(i);
			double val = calculaMedia(product);
			ProductosVal nuevo = new ProductosVal(product,val);
			productos.add(nuevo);
		}
	}
	
	public double calculaMedia(Productos producto) {
		List<Valoraciones> valor = serValoraciones.getValoracionesFromProducto(producto.getId());
		if(valor.size() == 0) {
			return 0.0;
		}else {
			double media=0.0;
			for(int i=0; i<valor.size(); i++) {
				media += valor.get(i).getValoracion();
			}
			return media/valor.size();
		}
	}
	
	@GetMapping("/busqueda")
	public String buscaProductos(@RequestParam String busca,Model model) {
		System.out.println("Buscando...");
		List<Productos>prod = (List<Productos>)serProductos.busquedaProductos(busca);
		rellenaProductos(prod);
		redireccion = "/busqueda?busca="+busca;
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/precio")
	public String orderPrecio(Model model) {
		orderByPrecio();
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/valoracion")
	public String orderValoracion(Model model) {
		orderByValoracion();
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/putcarrito/{id}")
	public String putOnCarrito(@PathVariable("id") int id,HttpSession sesion) {
		Productos prod = serProductos.getProductoFromId(id);
		List<Productos> carrito = (List<Productos>) sesion.getAttribute("carrito");
		carrito.add(prod);
		sesion.setAttribute("carrito", carrito);
		return "redirect:"+redireccion;
	}
	
	@GetMapping("/vercarrito")
	public String verCarrito(){
		return "carrito";
	}
	
	@GetMapping("/verdetalles/{id}")
	public String verDetalles(@PathVariable("id") int id,Model model) {
		Productos prod = serProductos.getProductoFromId(id);
		double valoracion = calculaMedia(prod);
		model.addAttribute("valoracion",valoracion);
		model.addAttribute("producto",prod);
		return "detalles";
	}
	
	public void orderByPrecio(){
	     int i, j;
	     ProductosVal aux;
	     if(precio) {
	    	 for (i = 0; i < productos.size() - 1; i++) {
		            for (j = 0; j < productos.size() - i - 1; j++) {
		                if (productos.get(j+1).getProducto().getPrecio() < productos.get(j).getProducto().getPrecio()) {
		                    aux = productos.get(j+1);
		                    productos.set(j+1, productos.get(j));
		                    productos.set(j, aux);
		                }
		            }
		        }
	    	 precio = !precio;
	     }else {
	    	 for (i = 0; i < productos.size() - 1; i++) {
		            for (j = 0; j < productos.size() - i - 1; j++) {
		                if (productos.get(j+1).getProducto().getPrecio() >= productos.get(j).getProducto().getPrecio()) {
		                    aux = productos.get(j+1);
		                    productos.set(j+1, productos.get(j));
		                    productos.set(j, aux);
		                }
		            }
		        }
	    	 precio = !precio;
	     }
	}
	
	public void orderByValoracion() {
		 int i, j;
	     ProductosVal aux;
	     if(valoracion) {
	    	 for (i = 0; i < productos.size() - 1; i++) {
		            for (j = 0; j < productos.size() - i - 1; j++) {
		                if (productos.get(j+1).getValoracionMedia() < productos.get(j).getValoracionMedia()) {
		                    aux = productos.get(j+1);
		                    productos.set(j+1, productos.get(j));
		                    productos.set(j, aux);
		                }
		            }
		        }
	    	 valoracion = !valoracion;
	     }else {
	    	 for (i = 0; i < productos.size() - 1; i++) {
		            for (j = 0; j < productos.size() - i - 1; j++) {
		                if (productos.get(j+1).getValoracionMedia() >= productos.get(j).getValoracionMedia()) {
		                    aux = productos.get(j+1);
		                    productos.set(j+1, productos.get(j));
		                    productos.set(j, aux);
		                }
		            }
		        }
	    	 valoracion = !valoracion;
	     }
	}

}
