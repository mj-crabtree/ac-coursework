package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.order.CustomerOrder;
import com.crabtree.hoyfc.model.order.OrderLineItem;
import com.crabtree.hoyfc.model.order.OrderStatus;
import com.crabtree.hoyfc.model.order.ShippingType;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.*;
import com.github.javafaker.Faker;
import org.joda.time.DateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
@Order(3)
public class OrdersBootstrap implements CommandLineRunner {

	private final CustomerService customerService;
	private final ProductService productService;
	private final OrderService orderService;
	private final Faker faker;

	private List<CustomerOrder> orders;

	public OrdersBootstrap(CustomerService customerService, ProductService productService, OrderService orderService, Faker faker) {
		this.customerService = customerService;
		this.productService = productService;
		this.orderService = orderService;
		this.faker = faker;
		this.orders = new ArrayList<>();
	}

	@Override
	public void run(String... args) throws Exception {
		for (Customer customer : customerService.getCustomers()) {

			// how many orders have they placed?
			var pastOrdersPlaced = faker
					.number()
					.numberBetween(1, 5);

			// which items did they order
			var lineItems = faker
					.number()
					.numberBetween(1, 10);

			// how many of those items did they order?
			var itemsPerLine = faker
					.number()
					.numberBetween(1, 5);

			for (int i = 0; i < pastOrdersPlaced; i++) {
				CustomerOrder order = buildNewCustomerOrder(customer, lineItems, itemsPerLine);
				order.setId(this.orders.size() + 1);
				orders.add(order);
			}
		}
		System.out.println();
	}

	private CustomerOrder buildNewCustomerOrder(Customer customer, int lineItems, int itemsPerLine) {

		var customerOrder = new CustomerOrder();
		double orderedItemsTotalCost = 0.0;

		customerOrder.setPublicOrderId(OrderIdService.getNextOrderId());
		customerOrder.setCustomer(customer);

		for (int i = 0; i < lineItems; i++) {

			var orderLineItem = new OrderLineItem();

			orderLineItem.setOrderId(customerOrder.getId());

			Product randomProduct = productService.getProductByIndex(faker
					.number()
					.numberBetween(0, 100));

			orderLineItem.setProduct(randomProduct);
			orderLineItem.setCount(itemsPerLine);

			var totalLineAmount = randomProduct
					.getProductPrice()
					.getProductPrice() * itemsPerLine;

			orderLineItem.setTotalCost(totalLineAmount);
			customerOrder.addLineItem(orderLineItem);
			orderedItemsTotalCost += totalLineAmount;
		}

		customerOrder.setLineItemsTotalCost(roundDoubleToTwoDecimalPlaces(orderedItemsTotalCost));
		customerOrder.setOrderDateTime(getRandomDateTime());
		customerOrder.setShippingType(ShippingType.getRandomShippingType());
		customerOrder.setShippingCost(ShippingCostService.getShippingCost(customerOrder.getShippingType()));
		customerOrder.setTotalOrderCost(roundDoubleToTwoDecimalPlaces(orderedItemsTotalCost + customerOrder.getShippingCost()));
		customerOrder.setOrderStatus(getRandomOrderStatus(customerOrder.getOrderDateTime()));
		return customerOrder;
	}

	private OrderStatus getRandomOrderStatus(DateTime orderDateTime) {
		if (orderDateTime.isAfter(1649721600)) {
			return OrderStatus.FULFILLED;
		}
		else {
			return OrderStatus.PENDING;
		}
	}

	private double roundDoubleToTwoDecimalPlaces(double input) {
		return Math.round(input * 100) / 100.0;
	}

	private DateTime getRandomDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		var beginTime = Timestamp.valueOf("2015-01-01 00:00:00").getTime();
		var endTime = Timestamp.valueOf("2022-04-15 00:58:00").getTime();

		long diff = endTime - beginTime + 1;

		return new DateTime(beginTime + (long) (Math.random() * diff));
	}
}