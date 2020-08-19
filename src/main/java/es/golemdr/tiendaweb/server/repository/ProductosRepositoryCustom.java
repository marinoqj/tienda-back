package es.golemdr.tiendaweb.server.repository;

import java.util.List;

import es.golemdr.tiendaweb.server.domain.Producto;

public interface ProductosRepositoryCustom {
	
	List<Producto> getProductosPorPrecio(Double limiteInferior, Double limiteSuperior);

}
