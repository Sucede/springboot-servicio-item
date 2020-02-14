package com.sucede.sb.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sucede.sb.app.item.models.Item;
import com.sucede.sb.app.item.models.Product;
import com.sucede.sb.app.item.models.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;

	@GetMapping("/list")
	public List<Item> list(){
		return itemService.findAll();
	}
	
	/* Con esta anotación en caso de fallo, irá al método registrado 
	 * en fallbackMethod*/
	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/view/{id}/quantity/{quantity}")
	public Item detail(@PathVariable Long id, @PathVariable Integer quantity) {
		return itemService.findById(id, quantity);
	}
	
	public Item metodoAlternativo (Long id, Integer quantity) {
		Item item = new Item();
		Product product = new Product();
		
		item.setQuantity(quantity);
		product.setId(id);
		product.setNombre("Sony Camera");
		product.setPrecio(725.99);
		item.setProduct(product);
		
		return item;
	}
}
