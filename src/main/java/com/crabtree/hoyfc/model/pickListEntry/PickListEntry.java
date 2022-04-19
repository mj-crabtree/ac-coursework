package com.crabtree.hoyfc.model.pickListEntry;

import com.crabtree.hoyfc.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickListEntry implements Comparable<PickListEntry> {
	private Product product;
	private Integer quantityToPick;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantityToPick() {
		return quantityToPick;
	}

	public void setQuantityToPick(Integer quantityToPick) {
		this.quantityToPick = quantityToPick;
	}

	@Override
	public int compareTo(PickListEntry o) {
		return this.product
				.getProductSku()
				.getProductSku()
				.compareTo(o
						.getProduct()
						.getProductSku()
						.getProductSku());
	}
}