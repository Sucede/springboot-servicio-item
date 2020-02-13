package com.sucede.sb.app.item.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sucede.sb.app.item.models.Product;

/*spring.application.name del Servicio Productos*/
/*sin robbon*/
//@FeignClient(name = "products-service", url = "localhost:8001")
/*con ribbon no necesitamos la url y el puerto. Lo hacemos en el application.properties*/
@FeignClient(name = "products-service")
public interface IProductClientRest {

	@GetMapping("/list")
	public List<Product> list();
	
	@GetMapping("/view/{id}")
	public Product product(@PathVariable Long id);
}
