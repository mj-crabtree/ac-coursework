package com.crabtree.hoyfc.model.order;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicDataStructure;
import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.product.Product;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerOrder extends BaseEntity {
	private Customer orderCustomer;
	private DynamicDataStructure<Product> orderItems;
	private OrderStatus orderStatus;
	private LocalDateTime orderDateTime;
	private Double orderAmount;
	private OrderPlatform orderPlatform;
	private ShippingType shippingType;
	private Double shippingAmount;
	private Double orderTotal;
}