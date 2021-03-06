package tienda.arturo.hernandez.controllers;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import tienda.arturo.hernandez.services.CategoriasService;
import tienda.arturo.hernandez.services.MenuService;
import tienda.arturo.hernandez.services.ProductosService;
import tienda.arturo.hernandez.services.UsuariosService;
import tienda.arturo.hernandez.services.ValoracionesService;
import tienda.arturo.hernandez.utilidades.UsoLogger;

@Controller
@RequestMapping("")
public class IndexController {
	
	@Autowired
	private ProductosService serProductos;
	
	@Autowired
	private ValoracionesService serValoraciones;
	
	@Autowired
	private CategoriasService serCategorias;
	
	Logger log = UsoLogger.getLogger(IndexController.class);
	
	private ArrayList<ProductosVal> productos =new ArrayList<ProductosVal>();
	private static boolean precio=true;
	private static boolean valoracion=true;
	protected static boolean carritoBool=true;
	protected static String redireccion="/";
	
	
	private void iniciaCarrito(HttpSession sesion) {
		ArrayList<ProductosPedido> carrito = new ArrayList<ProductosPedido>();
		log.info("Iniciando carrito");
		sesion.setAttribute("carrito",carrito);
		carritoBool=false;
	}
	
	
	@GetMapping("")
	public String goIndex(Model model,HttpSession sesion) {
		List<Categorias> categorias = serCategorias.getListaCategorias();
		sesion.setAttribute("listaCategorias", categorias);
		//Usuarios user = (Usuarios)model.asMap().get("user");
		if(carritoBool) {
			iniciaCarrito(sesion);
		}
		redireccion="/";
		List<Productos> prod = (List<Productos>)serProductos.getListaProductos();
		rellenaProductos(prod);
		log.info("Iniciando app");
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
		log.info("Productos rellenados");
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
			double redondeo = Math.round(media/valor.size());
			log.info("Media calculada");
			return redondeo;
			
		}
		
	}
	
	@GetMapping("/busqueda")
	public String buscaProductos(@RequestParam String busca,@RequestParam String precio,@RequestParam String categoria,Model model) {
		System.out.println("Buscando...");
		int cate = Integer.parseInt(categoria);
		if(cate == 0) {
			if(precio.equals("")) {
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductos(busca);
				rellenaProductos(prod);
			}else if(busca.equals("")){
				double prec = Double.parseDouble(precio);
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductosPrecio(prec);
				rellenaProductos(prod);
			}else {
				double prec = Double.parseDouble(precio);
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductosPrecio(busca,prec);
				rellenaProductos(prod);
			}
			log.info("Busqueda sin categorias");
		}else {
			if(precio.equals("")) {
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductos(busca,cate);
				rellenaProductos(prod);
			}else if(busca.equals("")){
				double prec = Double.parseDouble(precio);
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductosPrecio(prec,cate);
				rellenaProductos(prod);
			}else {
				double prec = Double.parseDouble(precio);
				List<Productos>prod = (List<Productos>)serProductos.busquedaProductosPrecio(busca,prec,cate);
				rellenaProductos(prod);
			}
			log.info("Busqueda con categorias");
		}
		
		redireccion = "/busqueda?busca="+busca+"&precio="+precio+"&categoria="+categoria;
		
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/precio")
	public String orderPrecio(Model model) {
		orderByPrecio();
		log.info("Ordenando por precio");
		redireccion = "/precio";
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/valoracion")
	public String orderValoracion(Model model) {
		orderByValoracion();
		log.info("Ordenando por valoracion");
		redireccion = "/valoracion";
		model.addAttribute("productos",productos);
		return "index";
	}
	
	@GetMapping("/verdetalles/{id}")
	public String verDetalles(@PathVariable("id") int id,Model model) {
		Productos prod = serProductos.getProductoFromId(id);
		double valoracion = calculaMedia(prod);
		model.addAttribute("valoracion",valoracion);
		model.addAttribute("producto",prod);
		log.info("Entrando en detalles del producto"+prod.toString());
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
