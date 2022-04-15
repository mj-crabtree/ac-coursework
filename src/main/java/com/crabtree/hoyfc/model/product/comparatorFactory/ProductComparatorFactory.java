package com.crabtree.hoyfc.model.product.comparatorFactory;

import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.pageSort.SortDirection;

import java.util.Comparator;

public class ProductComparatorFactory {
	public static Comparator<Product> getComparator(String sortColumn, SortDirection sortDirection) {
		switch (sortColumn) {
			case "Id":
				return getProductIdComparator(sortDirection);
			case "Sku":
				return getSkuComparator(sortDirection);
			case "Name":
				return getProductNameComparator(sortDirection);
			case "Type":
				return getProductTypeComparator(sortDirection);
			case "Status":
				return getProductStatusComparator(sortDirection);
			case "Count":
				return getProductCountComparator(sortDirection);
			case "Price":
				return getProductPriceComparator(sortDirection);
			default:
				throw new IllegalStateException("Unexpected value: " + sortColumn);
		}
	}

	private static Comparator<Product> getProductIdComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductIdComparator.ascending() : ProductIdComparator.descending();
	}

	private static Comparator<Product> getProductPriceComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductPriceComparator.ascending() : ProductPriceComparator.descending();
	}

	private static Comparator<Product> getProductCountComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductCountComparator.ascending() : ProductCountComparator.descending();
	}

	private static Comparator<Product> getProductStatusComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductStatusComparator.ascending() : ProductStatusComparator.descending();
	}

	private static Comparator<Product> getProductTypeComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductTypeComparator.ascending() : ProductTypeComparator.descending();
	}

	private static Comparator<Product> getProductNameComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductNameComparator.ascending() : ProductNameComparator.descending();
	}

	private static Comparator<Product> getSkuComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? ProductSkuComparator.ascending() : ProductSkuComparator.descending();
	}

	private static class ProductPriceComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> Double.compare(o1
					.getProductPrice()
					.getProductPrice(), o2
					.getProductPrice()
					.getProductPrice());
		}

		public static Comparator<Product> descending() {

			return (o1, o2) -> Double.compare(o2
					.getProductPrice()
					.getProductPrice(), o1
					.getProductPrice()
					.getProductPrice());
		}
	}

	private static class ProductCountComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getStockCount()
					.getCurrentStock()
					.compareTo(o2
							.getStockCount()
							.getCurrentStock());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getStockCount()
					.getCurrentStock()
					.compareTo(o1
							.getStockCount()
							.getCurrentStock());
		}

	}

	private static class ProductStatusComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getProductStatus()
					.compareTo(o2.getProductStatus());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getProductStatus()
					.compareTo(o1.getProductStatus());
		}
	}

	private static class ProductTypeComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getProductType()
					.compareTo(o2.getProductType());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getProductType()
					.compareTo(o1.getProductType());
		}
	}

	private static class ProductNameComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getProductName()
					.getProductName()
					.compareTo(o2
							.getProductName()
							.getProductName());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getProductName()
					.getProductName()
					.compareTo(o1
							.getProductName()
							.getProductName());
		}
	}

	private static class ProductSkuComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getProductSku()
					.getProductSku()
					.compareTo(o2
							.getProductSku()
							.getProductSku());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getProductSku()
					.getProductSku()
					.compareTo(o1
							.getProductSku()
							.getProductSku());
		}
	}

	private static class ProductIdComparator {
		public static Comparator<Product> ascending() {
			return (o1, o2) -> o1
					.getId()
					.compareTo(o2.getId());
		}

		public static Comparator<Product> descending() {
			return (o1, o2) -> o2
					.getId()
					.compareTo(o1.getId());
		}
	}
}