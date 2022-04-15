package com.crabtree.hoyfc.model.order;

import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import com.crabtree.hoyfc.model.customer.Customer;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomerOrder extends BaseEntity {
	private String publicOrderId;
	private Integer customerId;
	private List<OrderLineItem> lineItems = new ArrayList<>();
	private OrderStatus orderStatus;
	private DateTime orderDateTime;
	private Double lineItemsTotalCost;
	private ShippingType shippingType;
	private Double shippingCost;
	private Double totalOrderCost;

	public void addLineItem(OrderLineItem lineItem) {
		this.lineItems.add(lineItem);
	}
}