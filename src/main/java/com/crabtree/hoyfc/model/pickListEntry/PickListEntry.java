package com.crabtree.hoyfc.model.pickListEntry;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickListEntry implements Comparable<PickListEntry> {
	private Product product;
	private Integer quantityToPick;
	// private CustomerOrder customerOrder;

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