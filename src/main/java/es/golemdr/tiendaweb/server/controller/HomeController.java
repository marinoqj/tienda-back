package es.golemdr.tiendaweb.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	

	
	@GetMapping("/checkEstado")
	public @ResponseBody String checkEstado() {
		// Probando un commit en Git
		return "STATUS: UP";		
	}
	
}
