package es.golemdr.tiendaweb.server.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.tiendaweb.server.domain.Categoria;
import es.golemdr.tiendaweb.server.domain.Producto;
import es.golemdr.tiendaweb.server.service.ProductosService;

@CrossOrigin
@RestController
public class ProductosController {
	
    @Autowired
    ObjectMapper objectMapper;
	
	@Resource
	private ProductosService productosService;
	
	
	@GetMapping("/listadoProductos")
	public @ResponseBody List<Producto> listadoProductos() {
		
		List<Producto> productos = null;

		productos = productosService.getProductos();
		
		System.out.println("Hemos encontrado " + productos.size() + " productos");
		
		return productos;		
	}
	
	@GetMapping("/recuperarProductosCategoria{id}")
	public @ResponseBody List<Producto> recuperarProductosCategoria(@PathVariable("id") Long idCategoria) {
		 
		List<Producto> productos = null;
		
		Producto producto = new Producto();
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		producto.setCategoria(categoria);
		
		Example<Producto> example = Example.of(producto);

		productos = productosService.getProductosPorCategoria(example);
		
		return productos;		
	}
	
	@GetMapping("/recuperarProducto{id}")
	public ResponseEntity<?> recuperarProducto(@PathVariable("id") Long idProducto) {
		
		ResponseEntity<?> resultado = null;
		Producto producto = null;
		
		try {
			
			producto = productosService.getProductoById(idProducto);
			
			// TODO - Cambiar por log
			System.out.println(objectMapper.writeValueAsString(producto));
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No se encontró ningún producto con ID " + idProducto, HttpStatus.NOT_FOUND);			
		}

		return resultado;
	}
	
	@PostMapping("/crearProducto")
	public ResponseEntity<?> crearProducto(@RequestBody Producto producto) throws JsonProcessingException {
		
		// TODO - Cambiar por log
		System.out.println(objectMapper.writeValueAsString(producto));

		producto = productosService.insertarActualizarProducto(producto);

		return new ResponseEntity<Producto>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarProducto")
	public ResponseEntity<?> actualizarProducto(@RequestBody Producto producto) {

		ResponseEntity<?> resultado = null;
		
		try {
			
			// TODO - Cambiar por log
			System.out.println(objectMapper.writeValueAsString(producto));
						
			producto = productosService.insertarActualizarProducto(producto);
			
			resultado = new ResponseEntity<Producto>(producto, HttpStatus.OK);
			
		}catch (Exception e) {	
						
			// TODO - Añadir log
			resultado =  new ResponseEntity<String>("No Constante found for ID " + producto.getIdProducto(), HttpStatus.NOT_FOUND);			
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
	
	@GetMapping("/recuperarCategorias")
	public @ResponseBody List<Categoria> listadoCategorias() {
		
		List<Categoria> categorias = null;

		categorias = productosService.getCategorias();
		
		return categorias;		
	}
}
