package com.crabtree.hoyfc.service;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.CreateCustomerParameters;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerIdService customerIdService;

	public CustomerService(CustomerRepository customerRepository, CustomerIdService customerIdService) {
		this.customerRepository = customerRepository;
		this.customerIdService = customerIdService;
	}

	public void customCreateCustomer(CreateCustomerParameters customer) {
		Customer newCustomer = new Customer();

		newCustomer.setId(customerIdService.getNext());
		newCustomer.setCustomerName(customer.getUserName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setGender(customer.getGender());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setBirthday(customer.getBirthday());

		customerRepository.save(newCustomer);
	}

	public Customer getCustomer(Integer id) {
		return customerRepository.getById(id);
	}

	public Customer createCustomer(CreateCustomerParameters customer) {

		Customer newCustomer = new Customer();

		newCustomer.setCustomerName(customer.getUserName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setGender(customer.getGender());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setBirthday(customer.getBirthday());

		return customerRepository.save(newCustomer);
	}

	public Customer updateCustomer(Integer id, Customer request) {
		Customer customerById = customerRepository.getById(id);

		customerById.setCustomerName(request.getCustomerName());
		customerById.setEmail(request.getEmail());
		customerById.setEmail(request.getEmail());
		customerById.setBirthday(request.getBirthday());
		customerById.setActive(request.isActive());
		customerById.setPhoneNumber(request.getPhoneNumber());
		customerById.setUpdatedAt(LocalDateTime.now());

		return customerRepository.save(customerById);
	}

	public DynamicArrayList<Customer> getCustomers() {
		return customerRepository.getCustomers();
	}

	public Integer getTotalCustomers() {
		return customerRepository.count();
	}

	public DynamicArrayList<Customer> getSortedPaginatedCustomers(Integer pageNumber, Integer pageSize, String sortColumn, String sortDirection) {
		return customerRepository.getSortedPaginatedCustomers(pageNumber, pageSize, sortColumn, sortDirection);
	}
}