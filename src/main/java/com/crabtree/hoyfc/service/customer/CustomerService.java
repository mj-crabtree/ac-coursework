package com.crabtree.hoyfc.service.customer;

import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.customer.createCustomer.CreateCustomerParameters;
import com.crabtree.hoyfc.service.factory.ModelFactory;
import com.crabtree.hoyfc.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final CustomerIdService customerIdService;

	public CustomerService(CustomerRepository customerRepository, CustomerIdService customerIdService) {
		this.customerRepository = customerRepository;
		this.customerIdService = customerIdService;
	}

	public void createCustomer(CreateCustomerParameters customer) {
		var newCustomer = ModelFactory.newCustomer();

		newCustomer.setId(customerIdService.getNext());
		newCustomer.setCustomerName(customer.getUserName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setAddress(customer.getCustomerAddress());
		newCustomer.setGender(customer.getGender());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setBirthday(customer.getBirthday());

		customerRepository.save(newCustomer);
	}

	public Customer getCustomer(Integer id) {
		return customerRepository.getById(id);
	}

	public Customer updateCustomer(Integer id, Customer request) {
		Customer customerById = customerRepository.getById(id);

		customerById.setCustomerName(request.getCustomerName());
		customerById.setEmail(request.getEmail());
		customerById.setEmail(request.getEmail());
		customerById.setBirthday(request.getBirthday());
		customerById.setActive(request.isActive());
		customerById.setPhoneNumber(request.getPhoneNumber());

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

	public DynamicArrayList<Customer> search(String searchTerm) {
		return customerRepository.search(searchTerm);
	}
}