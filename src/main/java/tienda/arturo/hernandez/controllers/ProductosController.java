package tienda.arturo.hernandez.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.models.Productos;


@Controller
@RequestMapping("/productos")
public class ProductosController {
	
	ArrayList<Productos> prod = getAllProducts();
	
	
	
	
	public static ArrayList<Productos> getAllProducts(){
		ArrayList<Productos> productos = new ArrayList<Productos>();
		
		for(int i=0;i<10;i++) {
			Productos producto = new Productos(i+1,"Reloj","Reloj bonito",120.0,150,null,21);
			productos.add(producto);
		}
		
		return productos;
		
	}

}
