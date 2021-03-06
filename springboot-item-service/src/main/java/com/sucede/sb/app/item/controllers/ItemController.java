package com.sucede.sb.app.item.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sucede.sb.app.item.models.Item;
import com.sucede.sb.app.commons.models.entity.Product;
import com.sucede.sb.app.item.models.service.ItemService;

@RefreshScope
@RestController
public class ItemController {
	
	private static Logger log = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("serviceFeign")
	//@Qualifier("serviceRestTemplate")
	private ItemService itemService;
	
	@Value("${configuracion.texto}")
	private String text;
	
	@Value("${configuracion.autor.mail}")
	private String mail;

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
	
	@GetMapping("/read-config")
	public ResponseEntity<?> readConfig(@Value("${server.port}") String port){
		
		log.info(text);
		
		Map<String, String> json = new HashMap<>();
		json.put("texto", text);
		json.put("puerto", port);
		
		if (env.getActiveProfiles().length>0 && env.getActiveProfiles()[0].equals("dev")) {
			json.put("autor.nombre", env.getProperty("configuracion.autor.nombre"));
			json.put("autor.mail",mail);
		}
			
		
		
		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody Product product) {
		return itemService.save(product);
	}
	
	@PutMapping("/edit/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Product edit(@RequestBody Product product, @PathVariable Long id) {
		return itemService.update(product, id);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		itemService.Delete(id);
	}
	
}
