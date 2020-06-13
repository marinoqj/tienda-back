package es.golemdr.tiendaweb.server.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.golemdr.tiendaweb.server.domain.Categoria;
import es.golemdr.tiendaweb.server.domain.Producto;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductosControllerTest {
	
   
	private static final Long ID_PRODUCTO = 16L;
	private static final Long ID_CATEGORIA = 2L;
	
	@Autowired
    MockMvc mockMvc; 
	
    @Autowired
    ObjectMapper objectMapper;
    
    
    @Test
    public void recuperarProducto() throws Exception {
    	
    	
        mockMvc.perform(get("/recuperarProducto" + ID_PRODUCTO.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.idProducto", is(16)))
        .andExpect(jsonPath("$.nombre", is("Alitas")))
        .andExpect(jsonPath("$.precio", is(3.25)));
    	
    }
    
	@Test
	@Transactional
	public void crearProducto() throws Exception {
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(1L);
		Producto producto = new Producto();
		producto.setCategoria(categoria);
		producto.setNombre("Prueba");
		producto.setPrecio(2.99);
		producto.setNombreFoto("pruebafoto");

		
		String productoJson = objectMapper.writeValueAsString(producto);
		
        mockMvc.perform(post("/crearProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productoJson))
                .andExpect(status().isCreated());
		
		
	}
	
	@Test
	@Transactional
	public void actualizarProducto() throws Exception {
		
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(2L);
		Producto producto = new Producto();
		producto.setIdProducto(ID_PRODUCTO);
		producto.setCategoria(categoria);
		producto.setNombre("Alitasss");
		producto.setPrecio(3.25);
		producto.setNombreFoto("pruebafoto");
		
		String productoJson = objectMapper.writeValueAsString(producto);
		
        mockMvc.perform(put("/actualizarProducto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productoJson))
                .andExpect(status().isOk());
		
		
	}

    @Test
    @Transactional
    public void borrarProducto() throws Exception {
    	
    	
        mockMvc.perform(delete("/borrarProducto" + ID_PRODUCTO.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    	
    }
    
	@Test
	public void listadoProductos() throws Exception {
		
		assertNotNull(mockMvc);
	
        mockMvc.perform(get("/listadoProductos").accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))	       
	        .andExpect(jsonPath("$[0].idProducto", is(16)))
	        .andExpect(jsonPath("$[0].nombre", is("Alitas")));
        
	}
	
    @Test
    public void recuperarProductosCategoria() throws Exception {
    	
    	
        mockMvc.perform(get("/recuperarProductosCategoria" + ID_CATEGORIA.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].idProducto", is(16)))
        .andExpect(jsonPath("$[0].nombre", is("Alitas")));
    	
    }
    
}
