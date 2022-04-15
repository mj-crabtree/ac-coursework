package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderLineItem;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.model.customerOrder.ShippingType;
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

			// how many unique items did this customer order?
			int lineItems = getRandomIntegersBetween(1, 10);

			CustomerOrder order = buildNewCustomerOrder(customer, lineItems);
			order.setId(orderService.getOrderCount() + 1);
			// orders.add(order);
			orderService.save(order);
		}
	}

	private CustomerOrder buildNewCustomerOrder(Customer customer, int lineItems) {

		var customerOrder = new CustomerOrder();
		double orderedItemsTotalCost = 0.0;

		customerOrder.setPublicOrderId(OrderIdService.getNextOrderId());
		customerOrder.setCustomerId(customer.getId());

		for (int i = 0; i < lineItems; i++) {

			var orderLineItem = new OrderLineItem();

			orderLineItem.setOrderId(this.orders.size() + 1);

			Product randomProduct = getRandomProductId();

			orderLineItem.setProduct(randomProduct);

			var itemsPerLine = getRandomIntegersBetween(1, 3);

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

		customer.addOrderToOrderHistory(customerOrder);
		customerOrder.setOrderCustomer(customer);

		return customerOrder;
	}

	private Integer getRandomIntegersBetween(Integer min, Integer max) {
		return this.faker
				.number()
				.numberBetween(min, max);
	}

	private Product getRandomProductId() {
		return productService.getProductByIndex(getRandomIntegersBetween(0, 100));
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

		var beginTime = Timestamp
				.valueOf("2015-01-01 00:00:00")
				.getTime();
		var endTime = Timestamp
				.valueOf("2022-04-15 00:58:00")
				.getTime();

		long diff = endTime - beginTime + 1;

		return new DateTime(beginTime + (long) (Math.random() * diff));
	}
}