package es.golemdr.tiendaweb.server.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import es.golemdr.tiendaweb.server.domain.Cliente;
import es.golemdr.tiendaweb.server.domain.Detalle;
import es.golemdr.tiendaweb.server.domain.Pedido;
import es.golemdr.tiendaweb.server.domain.Producto;


@SpringBootTest
@AutoConfigureMockMvc
public class PedidosControllerTest {
	
   
	private static final Long ID_PEDIDO = 12L;
	private static final String DNI_CLIENTE = "1234567C";
	
	
	@Autowired
    MockMvc mockMvc; 
	
    @Autowired
    ObjectMapper objectMapper;
    
    
    @Test
    public void recuperarPedido() throws Exception {
    	
    	
        mockMvc.perform(get("/recuperarPedido" + ID_PEDIDO.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.idPedido", is(12)))
        .andExpect(jsonPath("$.total", is(29.48)))
        .andExpect(jsonPath("$.numArticulos", is(6)));
    	
    }
    
	@Test
	@Transactional
	public void crearPedido() throws Exception {
		
		Pedido pedido = new Pedido();
		pedido.setTotal(12.88);
		pedido.setNumArticulos(5);
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1L);
		pedido.setCliente(cliente);
		
		Detalle detalle = new Detalle();
		detalle.setPrecio(25.77);
		detalle.setCantidad(4);
		Producto producto = new Producto();
		producto.setIdProducto(16L);  // Alitas
		detalle.setProducto(producto);
		
		pedido.getDetalles().add(detalle);
		
				
		String pedidoJson = objectMapper.writeValueAsString(pedido);
		
        mockMvc.perform(post("/crearPedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pedidoJson))
                .andExpect(status().isCreated());
		
		
	}
	
	@Test
	@Transactional
	public void actualizarPedido() throws Exception {
		
		Pedido pedido = new Pedido();
		pedido.setIdPedido(ID_PEDIDO);
		pedido.setTotal(12.88);
		pedido.setNumArticulos(5);
		Cliente cliente = new Cliente();
		cliente.setIdCliente(1L);
		pedido.setCliente(cliente);
		
		Detalle detalle = new Detalle();
		detalle.setPrecio(25.77);
		detalle.setCantidad(4);
		Producto producto = new Producto();
		producto.setIdProducto(16L);  // Alitas
		detalle.setProducto(producto);
		
		pedido.getDetalles().add(detalle);		
		
		String pedidoJson = objectMapper.writeValueAsString(pedido);
		
        mockMvc.perform(put("/actualizarPedido")
                .contentType(MediaType.APPLICATION_JSON)
                .content(pedidoJson))
                .andExpect(status().isOk());
		
		
	}

    @Test
    @Transactional
    public void borrarPedido() throws Exception {
    	
    	
        mockMvc.perform(delete("/borrarPedido" + ID_PEDIDO.toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    	
    }
    
	@Test
	public void listadoPedidos() throws Exception {
		
		assertNotNull(mockMvc);
	
        mockMvc.perform(get("/listadoPedidos").accept(MediaType.APPLICATION_JSON))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON))	       
	        .andExpect(jsonPath("$[0].idPedido", is(12)))
	        .andExpect(jsonPath("$[0].total", is(29.48)));
        
	}
	
    @Test
    public void recuperarClientePorDNI() throws Exception {
    	
    	
        mockMvc.perform(get("/recuperarClienteDNI" + DNI_CLIENTE).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.idCliente", is(1)));
    	
    }
	

    
}
