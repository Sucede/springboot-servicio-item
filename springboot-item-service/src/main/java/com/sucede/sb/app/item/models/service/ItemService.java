package com.sucede.sb.app.item.models.service;

import java.util.List;

import com.sucede.sb.app.item.models.Item;

public interface ItemService {

	public List<Item> findAll();
	public Item findById(Long id, Integer quantity);
}
