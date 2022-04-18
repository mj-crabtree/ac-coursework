package com.crabtree.hoyfc.bootstrap;

import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.customerOrder.CustomerOrder;
import com.crabtree.hoyfc.model.customerOrder.OrderLineItem;
import com.crabtree.hoyfc.model.customerOrder.OrderStatus;
import com.crabtree.hoyfc.model.customerOrder.ShippingType;
import com.crabtree.hoyfc.model.product.Product;
import com.crabtree.hoyfc.service.customer.CustomerService;
import com.crabtree.hoyfc.service.order.OrderIdService;
import com.crabtree.hoyfc.service.order.OrderService;
import com.crabtree.hoyfc.service.order.OrderShippingCostService;
import com.crabtree.hoyfc.service.product.ProductService;
import com.github.javafaker.Faker;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
@Order(3)
public class OrdersBootstrap implements CommandLineRunner {

	private final CustomerService customerService;
	private final ProductService productService;
	private final OrderService orderService;
	private final Faker faker;
	private final List<CustomerOrder> orders;
	private org.joda.time.LocalDate orderDate;

	public OrdersBootstrap(CustomerService customerService, ProductService productService, OrderService orderService, Faker faker) {
		this.customerService = customerService;
		this.productService = productService;
		this.orderService = orderService;
		this.faker = faker;
		this.orders = new ArrayList<>();
		this.orderDate = LocalDate.parse("2022-01-01");
	}

	@Override
	public void run(String... args) throws Exception {
		createFulfilledOrderHistory();
		createPendingOrders();
		var x = getSequentialDateTime();
		System.out.println();
	}

	private void createPendingOrders() {
		for (int i = 0; i < 5; i++) {
			var customer = customerService.getCustomer(i);
			var pendingOrder = buildNewCustomerOrder(customer, getRandomIntegersBetween(1, 5));
			pendingOrder.setOrderStatus(OrderStatus.PENDING);
			customer.addOrderToOrderHistory(pendingOrder);
			orderService.save(pendingOrder);
		}
	}

	private void createFulfilledOrderHistory() {
		for (Customer customer : customerService.getCustomers()) {
			var fulfilledOrder = buildNewCustomerOrder(customer, getRandomIntegersBetween(1, 10));
			fulfilledOrder.setOrderStatus(OrderStatus.FULFILLED);
			customer.addOrderToOrderHistory(fulfilledOrder);
			orderService.save(fulfilledOrder);
		}
	}

	private CustomerOrder buildNewCustomerOrder(Customer customer, int lineItems) {

		// todo: tidy this up!
		var customerOrder = new CustomerOrder();
		customerOrder.setId(orderService.getOrderCount() + 1);
		customerOrder.setPublicOrderId(OrderIdService.getNextOrderId());
		customerOrder.setCustomerId(customer.getId());

		double orderedItemsTotalCost = 0.0;

		for (int i = 0; i < lineItems; i++) {

			Product randomProduct = getRandomProductId();
			var orderLineItem = new OrderLineItem();
			orderLineItem.setOrderId(this.orders.size() + 1);
			orderLineItem.setProduct(randomProduct);

			var itemsPerLine = getRandomIntegersBetween(1, 3);
			orderLineItem.setCount(itemsPerLine);

			var totalLineAmount = randomProduct
					.getProductPrice()
					.getProductPrice() * itemsPerLine;

			orderedItemsTotalCost += totalLineAmount;
			orderLineItem.setTotalCost(totalLineAmount);
			customerOrder.addLineItem(orderLineItem);
		}

		customerOrder.setLineItemsTotalCost(roundDoubleToTwoDecimalPlaces(orderedItemsTotalCost));
		customerOrder.setOrderDateTime(getSequentialDateTime());
		customerOrder.setShippingType(ShippingType.getRandomShippingType());
		customerOrder.setShippingCost(OrderShippingCostService.getShippingCost(customerOrder.getShippingType()));
		customerOrder.setTotalOrderCost(roundDoubleToTwoDecimalPlaces(orderedItemsTotalCost + customerOrder.getShippingCost()));
		customerOrder.setOrderCustomer(customer);

		return customerOrder;
	}

	private Product getRandomProductId() {
		return productService.getProductByIndex(getRandomIntegersBetween(0, 100));
	}

	private LocalDateTime getSequentialDateTime() {

		incrementOrderDate();
		LocalTime orderTime = getRandomTime();
		return LocalDateTime.parse(getDateTimeString(orderTime), DateTimeFormat.forPattern("yyyy-MM-dd H.mm.ss"));
	}

	private String getDateTimeString(LocalTime orderTime) {
		var hour = orderTime.getHour();
		var minute = orderTime.getMinute();
		var second = orderTime.getSecond();

		return this.orderDate.toString() + " " + hour + "." + minute + "." + second;
	}

	private void incrementOrderDate() {
		this.orderDate = this.orderDate.plusDays(1);
	}

	private LocalTime getRandomTime() {
		Random random = new Random();
		var timeFormat = LocalTime.of(random.nextInt(24), random.nextInt(60),
				random.nextInt(60), random.nextInt(999999999 + 1));
		return timeFormat;
	}

	private Integer getRandomIntegersBetween(Integer min, Integer max) {
		return this.faker
				.number()
				.numberBetween(min, max);
	}

	private double roundDoubleToTwoDecimalPlaces(double input) {
		return Math.round(input * 100) / 100.0;
	}
}