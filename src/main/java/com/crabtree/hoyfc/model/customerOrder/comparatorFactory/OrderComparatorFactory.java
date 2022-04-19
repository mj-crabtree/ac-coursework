package com.crabtree.hoyfc.model.customerOrder.comparatorFactory;

import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.service.pageSort.SortDirection;

import java.util.Comparator;

public class OrderComparatorFactory {
	public static Comparator<CustomerOrder> getComparator(String sortColumn, SortDirection sortDirection) {
		switch (sortColumn) {
			case "Id":
				return getOrderIdComparator(sortDirection);
			case "Name":
				return getOrderNameComparator(sortDirection);
			case "Date":
				return getOrderDateComparator(sortDirection);
			case "Total":
				return getOrderTotalComparator(sortDirection);
			case "Status":
				return getOrderStatusComparator(sortDirection);
			default:
				throw new IllegalStateException("Unexpected value: " + sortColumn);
		}
	}

	private static Comparator<CustomerOrder> getOrderStatusComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? OrderStatusComparator.ascending() : OrderStatusComparator.descending();
	}

	private static Comparator<CustomerOrder> getOrderTotalComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? OrderTotalComparator.ascending() : OrderTotalComparator.descending();
	}

	private static Comparator<CustomerOrder> getOrderDateComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? OrderDateComparator.ascending() : OrderDateComparator.descending();
	}

	private static Comparator<CustomerOrder> getOrderNameComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? OrderNameComparator.ascending() : OrderNameComparator.descending();
	}

	private static Comparator<CustomerOrder> getOrderIdComparator(SortDirection sortDirection) {
		return sortDirection
				.getDirection()
				.equals("ASC") ? OrderIdComparator.ascending() : OrderIdComparator.descending();
	}

	private static class OrderStatusComparator {
		public static Comparator<CustomerOrder> ascending() {
			return (o1, o2) -> {
				int result = o1
						.getOrderStatus()
						.compareTo(o2.getOrderStatus());
				if (result != 0) {
					return result;
				}
				else {
					return o1
							.getOrderDateTime()
							.compareTo(o2.getOrderDateTime());
				}
			};
		}

		public static Comparator<CustomerOrder> descending() {
			return (o1, o2) -> {
				int result = o2
						.getOrderStatus()
						.compareTo(o1.getOrderStatus());
				if (result != 0) {
					return result;
				}
				else {
					return o2
							.getOrderDateTime()
							.compareTo(o1.getOrderDateTime());
				}
			};
		}
	}

	private static class OrderTotalComparator {
		public static Comparator<CustomerOrder> ascending() {
			return (o1, o2) -> o1
					.getTotalOrderCost()
					.compareTo(o2.getTotalOrderCost());
		}

		public static Comparator<CustomerOrder> descending() {
			return (o1, o2) -> o2
					.getTotalOrderCost()
					.compareTo(o1.getTotalOrderCost());
		}
	}

	private static class OrderDateComparator {
		public static Comparator<CustomerOrder> ascending() {
			return (o1, o2) -> o1
					.getOrderDateTime()
					.compareTo(o2.getOrderDateTime());
		}

		public static Comparator<CustomerOrder> descending() {
			return (o1, o2) -> o2
					.getOrderDateTime()
					.compareTo(o1.getOrderDateTime());
		}
	}

	private static class OrderNameComparator {
		public static Comparator<CustomerOrder> ascending() {
			return (o1, o2) -> o1
					.getOrderCustomer()
					.getCustomerName()
					.getFullName()
					.compareTo(o2
							.getOrderCustomer()
							.getCustomerName()
							.getFullName());
		}

		public static Comparator<CustomerOrder> descending() {
			return (o1, o2) -> o2
					.getOrderCustomer()
					.getCustomerName()
					.getFullName()
					.compareTo(o1
							.getOrderCustomer()
							.getCustomerName()
							.getFullName());
		}
	}

	private static class OrderIdComparator {
		public static Comparator<CustomerOrder> ascending() {
			return (o1, o2) -> o1
					.getPublicOrderId()
					.compareTo(o2.getPublicOrderId());
		}

		public static Comparator<CustomerOrder> descending() {
			return (o1, o2) -> o2
					.getPublicOrderId()
					.compareTo(o1.getPublicOrderId());
		}
	}
}