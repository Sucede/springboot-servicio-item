package com.sucede.sb.app.item.models.service;

import java.util.List;

import com.sucede.sb.app.item.models.Item;
import com.sucede.sb.app.commons.models.entity.Product;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer quantity);
	
	public Product save(Product product);
	
	public Product update(Product product, Long id);
	
	public void Delete(Long id);
}
