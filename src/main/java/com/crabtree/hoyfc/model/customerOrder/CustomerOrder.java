package com.crabtree.hoyfc.model.customerOrder;

import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import com.crabtree.hoyfc.model.customer.Customer;
import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerOrder extends BaseEntity implements Comparable<CustomerOrder> {
	private String publicOrderId;
	private Integer customerId;
	private Customer orderCustomer;
	private List<OrderLineItem> lineItems = new ArrayList<>();
	private OrderStatus orderStatus;
	private LocalDateTime orderDateTime;
	private Double lineItemsTotalCost;
	private ShippingType shippingType;
	private Double shippingCost;
	private Double totalOrderCost;

	public void addLineItem(OrderLineItem lineItem) {
		this.lineItems.add(lineItem);
	}


	@Override
	public String toString() {
		return "CustomerOrder{" +
				"publicOrderId='" + publicOrderId + '\'' +
				", customerId=" + customerId +
				", orderCustomer=" + orderCustomer.toString() +
				", lineItems=" + lineItems +
				", orderStatus=" + orderStatus +
				", orderDateTime=" + orderDateTime +
				", lineItemsTotalCost=" + lineItemsTotalCost +
				", shippingType=" + shippingType +
				", shippingCost=" + shippingCost +
				", totalOrderCost=" + totalOrderCost +
				'}';
	}

	@Override
	public int compareTo(CustomerOrder o) {
		return this
				.getPublicOrderId()
				.compareTo(o.getPublicOrderId());
	}
}