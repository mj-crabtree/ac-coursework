package com.crabtree.hoyfc.model.pickListEntry;

import com.crabtree.hoyfc.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PickListEntry {
	private Product product;
	private Integer quantityToPick;
}