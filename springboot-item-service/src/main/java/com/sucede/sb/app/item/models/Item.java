package com.sucede.sb.app.item.models;

import com.sucede.sb.app.commons.models.entity.Product;

public class Item {

	private Product product;
	private Integer quantity;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Item(Product product, Integer quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Item() {

	}

	public Double getTotal() {
		return product.getPrecio() *  quantity.doubleValue();
	}
}
