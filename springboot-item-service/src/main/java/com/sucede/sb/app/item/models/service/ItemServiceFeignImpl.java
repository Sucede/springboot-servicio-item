package com.sucede.sb.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.sucede.sb.app.item.clients.IProductClientRest;
import com.sucede.sb.app.item.models.Item;
import com.sucede.sb.app.commons.models.entity.Product;

@Service("serviceFeign")
@Primary
public class ItemServiceFeignImpl implements ItemService {
	
	@Autowired
	private IProductClientRest clientFeign;

	@Override
	public List<Item> findAll() {
		return clientFeign.list().stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer quantity) {
		return new Item(clientFeign.product(id),quantity);
	}

	@Override
	public Product save(Product product) {
		return clientFeign.create(product);
	}

	@Override
	public Product update(Product product, Long id) {
		return clientFeign.update(product, id);
	}

	@Override
	public void Delete(Long id) {
		clientFeign.delete(id);
		
	}

}
