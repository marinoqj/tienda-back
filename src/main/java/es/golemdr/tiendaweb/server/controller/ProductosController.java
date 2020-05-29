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

import es.golemdr.tiendaweb.server.domain.Producto;
import es.golemdr.tiendaweb.server.service.ProductosService;


@RestController
public class ProductosController {
	
	
	@Resource
	private ProductosService productosService;
	
	
	@GetMapping("/listadoProductos")
	public @ResponseBody List<Producto> listProductos() {
		
		List<Producto> productos = null;

		productos = productosService.getProductos();
		
		return productos;		
	}
	
	@GetMapping("/recuperarProducto{id}")
	public ResponseEntity<?> getProducto(@PathVariable("id") Long idProducto) {
		
		ResponseEntity<?> resultado = null;
		Producto producto = null;
		
		try {
			
			producto = productosService.getProductoById(idProducto);			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@PostMapping("/crearProducto")
	public ResponseEntity<?> createProducto(@RequestBody Producto producto) {

		producto = productosService.insertarActualizarProducto(producto);

		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarProducto{id}")
	public ResponseEntity<?> actualizarProducto(@PathVariable("id") Long idProducto, @RequestBody Producto producto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			// Si el producto no existe saltará un excepción
			productosService.getProductoById(idProducto);
			
			// Nos aseguramos que el id de la request y el del JSON son el mismo
			producto.setIdProducto(idProducto);			
			producto = productosService.insertarActualizarProducto(producto);
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No Constante found for ID " + idProducto, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@DeleteMapping("/borrarProducto{id}")
	public ResponseEntity<?> borrarProducto(@PathVariable("id") Long idProducto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			productosService.borrarProducto(idProducto);
			
			resultado = new ResponseEntity<String>("El producto se borró correctamente", HttpStatus.OK);
			
		}catch (Exception e) {
			
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);
		}
		
		
		return resultado;
		

	}
}
