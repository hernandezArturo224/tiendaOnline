package tienda.arturo.hernandez.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tienda.arturo.hernandez.models.Configuracion;
import tienda.arturo.hernandez.models.Pedidos;
import tienda.arturo.hernandez.services.ConfiguracionService;
import tienda.arturo.hernandez.services.PedidosService;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {

	@Autowired
	private PedidosService serPedidos;
	
	@Autowired
	private ConfiguracionService serConfig;
	
	@GetMapping("/cancelar")
	private String verPedidosCancelados(Model model) {
		List<Pedidos> pedidos = serPedidos.getPedidosCancelados();
		model.addAttribute("pedidos",pedidos);
		return "pedidos/cancelados";
	}
	
	@GetMapping("/cancelar/submit/{id}")
	public String eliminarPedidoCancelado(@PathVariable("id") int id) {
		Pedidos pedido = serPedidos.getPedidoById(id);
		serPedidos.eliminarPedido(pedido);
		
		return "redirect:/pedidos/cancelar";
	}
	
	@GetMapping("/enviar")
	public String verPedidosRealizados(Model model) {
		List<Pedidos> pedidos = serPedidos.getPedidosEnviar();
		model.addAttribute("pedidos",pedidos);
		return "pedidos/realizados";
	}
	
	@GetMapping("/enviar/submit/{id}")
	public String enviarPedido(@PathVariable("id") int id) {
		Pedidos pedido = serPedidos.getPedidoById(id);
		pedido.setEstado("enviado");
		Configuracion maxPedido = serConfig.getValorClave("numFactura");
		
		maxPedido.setValor(sumaUno(maxPedido.getValor()));
		pedido.setNum_factura("factura-"+maxPedido.getValor());
		serPedidos.guardarPedido(pedido);
		serConfig.actualizarConfig(maxPedido);
		
		return "redirect:/pedidos/enviar";
	}
	
	private String sumaUno(String max) {
		int suma = Integer.parseInt(max);
		suma++;
		return ""+suma;
	}
	
}
