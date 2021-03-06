package com.crabtree.hoyfc.service.product;

import com.crabtree.hoyfc.model.product.ProductColour;
import com.crabtree.hoyfc.model.product.ProductSku;
import com.crabtree.hoyfc.model.product.ProductType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductSkuService {

	private static String productTypeSkuifier(ProductType productType) {
		var string = productType.name();
		switch (string) {
			case "PIN":
				return "PN";
			case "PATCH":
				return "PH";
			case "STICKER":
				return "SR";
			default:
				throw new IllegalStateException("Unexpected value: " + string);
		}
	}

	private static String productNameSkuifier(String productName) {

		List<String> strings = getStrings(productName);

		var out = new StringBuilder();

		for (String string : strings) {
			for (int i = 0; i < 2 && out.length() < 4; i++) {
				out.append(string.charAt(i));
			}
		}
		return out.toString();
	}

	private static List<String> getStrings(String input) {
		var strings = Stream
				.of(input
						.toUpperCase()
						.split(" "))
				.map(substring -> substring)
				.collect(Collectors.toList());
		return strings;
	}

	public ProductSku createSku(ProductType productType, String productName, ProductColour productColour) {
		var sku = new StringBuilder();

		sku.append("FC-");
		sku
				.append(productTypeSkuifier(productType))
				.append("-");
		sku
				.append(productNameSkuifier(productName))
				.append("-");

		sku.append(String.format("%05d", ProductIdService.getNextProductSkuId()));

		return new ProductSku(sku.toString());
	}
}