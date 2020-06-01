package es.golemdr.tiendaweb.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.golemdr.tiendaweb.server.domain.Pedido;
import es.golemdr.tiendaweb.server.service.PedidosService;


@RestController
public class PedidosController {
	
	@Resource
	private PedidosService pedidosService;
	
	
	@GetMapping("/listadoPedidos")
	public @ResponseBody List<Pedido> listPedidos() {
		
		List<Pedido> pedidos = null;

		pedidos = pedidosService.getPedidos();
		
		return pedidos;		
	}
	
	@GetMapping("/recuperarPedido{id}")
	public ResponseEntity<?> getPedido(@PathVariable("id") Long idPedido) {
		
		ResponseEntity<?> resultado = null;
		Pedido pedido = null;
		
		try {
			
			pedido = pedidosService.getPedidoById(idPedido);			
			resultado = new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No se encontró ningún pedido con ID " + idPedido, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@PostMapping("/crearPedido")
	public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {

		pedido = pedidosService.insertarActualizarPedido(pedido);

		return new ResponseEntity<Pedido>(pedido, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarPedido{id}")
	public ResponseEntity<?> actualizarPedido(@PathVariable("id") Long idPedido, @RequestBody Pedido pedido) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			// Si el pedido no existe saltará un excepción
			pedidosService.getPedidoById(idPedido);
			
			// Nos aseguramos que el id de la request y el del JSON son el mismo
			pedido.setIdPedido(idPedido);			
			pedido = pedidosService.insertarActualizarPedido(pedido);
			
			resultado = new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No Constante found for ID " + idPedido, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@DeleteMapping("/borrarPedido{id}")
	public ResponseEntity<?> borrarPedido(@PathVariable("id") Long idPedido) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			pedidosService.borrarPedido(idPedido);
			
			resultado = new ResponseEntity<String>("El pedido se borró correctamente", HttpStatus.OK);
			
		}catch (Exception e) {
			
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No se encontró ningún pedido con ID " + idPedido, HttpStatus.NOT_FOUND);
		}
		
		
		return resultado;
		

	}
}
