package tienda.arturo.hernandez.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import tienda.arturo.hernandez.models.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpSession;

import tienda.arturo.hernandez.services.*;
import tienda.arturo.hernandez.utilidades.PDFHeaderFooter;
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
	
	@Autowired
	private RolesService serRoles;
	
	@GetMapping("")
	public String muestraPerfil(Model model) {
		List<Roles> roles = serRoles.getAllRoles();
		
		model.addAttribute("roles",roles);
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
		Pedidos pedidos = serPedidos.getPedidoById(id);
		
		model.addAttribute("estado",pedidos.getEstado());
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
	
	@GetMapping("/pdf/{id}")
	public String generarPDF(@PathVariable("id") int id) {
		Pedidos pedido = serPedidos.getPedidoById(id);
		List<Detalles_pedido> lineas = serDetalles.getDetallesFromPedido(pedido.getId());
		genPDF(pedido,lineas);
		
		//return "classpath:/static/pdf/factura-1.pdf";
		return "redirect:/perfil/pedidos";
	}
	
	
	public void genPDF(Pedidos pedido, List<Detalles_pedido> lineas) {
		PdfWriter writer = null;
		Document documento = new Document(PageSize.A4, 20, 20, 70, 50);
		Usuarios user = serUsuarios.getUserbyId(pedido.getUsuario());
		
	    try {      
	    	//Obtenemos la instancia del archivo a utilizar
	    	File fileLocation = new File("C:\\springWorkspace\\TIENDA_ARTURO_HERNANDEZ_NUNEZ\\src\\main\\resources\\static\\pdf\\"+pedido.getNum_factura()+".pdf");
	    	
	    	writer = PdfWriter.getInstance(documento, new FileOutputStream(fileLocation));
	    	
		    //Para insertar cabeceras/pies en todas las páginas
	    	writer.setPageEvent(new PDFHeaderFooter());
	        
		    //Abrimos el documento para edición
		    documento.open();
		    
		    //PARRAFOS
			Paragraph paragraph = new Paragraph();
			//String contenido = "esto es un párrafo";
			//paragraph.setSpacingBefore(100);
			paragraph.add(pedido.getNum_factura() +" "+user.getNombre()+" "+user.getApellido1()+" "+user.getApellido2());
			paragraph.add("\n\n");
		    
	    	documento.add(paragraph);
	    	
	    	
	    	//TABLAS
		    
			//Instanciamos una tabla de X columnas
		    PdfPTable tabla = new PdfPTable(5);
		    Phrase texto = new Phrase("Fecha");
			PdfPCell cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase("Metodo de pago");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase("Estado");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase("Numero de factura");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase("Total");
			cabecera = new PdfPCell(texto);
			cabecera.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase(pedido.getFecha().toString());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase(pedido.getMetodo_pago());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase(pedido.getEstado());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase(pedido.getNum_factura());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
			
			texto = new Phrase(""+pedido.getTotal());
			cabecera = new PdfPCell(texto);
			cabecera.setBorderWidth(1);
			tabla.addCell(cabecera);
		    documento.add(tabla);
		    
		    Paragraph parrafo = new Paragraph();
		    parrafo.add("\n\n");
		    parrafo.add("Detalles");
		    parrafo.add("\n\n");
		    documento.add(parrafo);
		    
		    PdfPTable tabla2 = new PdfPTable(5);
		    Phrase texto2 = new Phrase("Producto");
			PdfPCell cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			tabla2.addCell(cabecera2);
			
			texto2 = new Phrase("Precio Unitario");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			tabla2.addCell(cabecera2);
			
			texto2 = new Phrase("Unidades");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			tabla2.addCell(cabecera2);
			
			texto2 = new Phrase("Impuesto");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			tabla2.addCell(cabecera2);
			
			texto2 = new Phrase("Total");
			cabecera2 = new PdfPCell(texto2);
			cabecera2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cabecera2.setBorderWidth(1);
			tabla2.addCell(cabecera2);
			
			for(int i=0; i<lineas.size(); i++) {
				Detalles_pedido linea = lineas.get(i);
				Productos producto = serProductos.getProductoFromId(linea.getProducto());
				
				texto2 = new Phrase(producto.getNombre());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla2.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getPrecio_unidad());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla2.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getUnidades());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla2.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getImpuesto());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla2.addCell(cabecera2);
				
				texto2 = new Phrase(""+linea.getTotal());
				cabecera2 = new PdfPCell(texto2);
				cabecera2.setBorderWidth(1);
				tabla2.addCell(cabecera2);
			}
		    
		    
	    	documento.add(tabla2);
		    documento.close(); //Cerramos el documento
		    writer.close(); //Cerramos writer
		    
			
	    } catch (Exception ex) {
	    	ex.getMessage();
	    	ex.printStackTrace();
	    }

	}

}
