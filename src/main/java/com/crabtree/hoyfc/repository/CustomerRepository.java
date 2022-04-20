package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearch;
import com.crabtree.customDSA.algorithms.search.recursiveBinarySearch.RecursiveBinarySearch;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.Customer;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomerRepository {
	private final DynamicArrayList<Customer> customers;

	public CustomerRepository() {
		this.customers = new DynamicArrayList<>();
	}

	public Customer getCustomerByIndex(int index) {
		return this.customers.getByIndex(index);
	}

	public Customer getCustomerById(int customerId) {
		var bs = new RecursiveBinarySearch();
		return getCustomerByIndex(bs.customerIdSearch(this.customers, customerId));
	}

	public Customer save(Customer customer) {
		return this.customers.add(customer);
	}

	public DynamicArrayList<Customer> getCustomers() {
		return this.customers;
	}

	public Integer count() {
		return this.customers.count();
	}

	public DynamicArrayList<Customer> search(String needle) {
		DynamicArrayList<Customer> result = new DynamicArrayList<>();

		for (Customer customer : customers) {
			var nameHaystack = customer
					.getCustomerName()
					.getFullName()
					.toLowerCase(Locale.ROOT);

			var emailHaystack = customer
					.getEmail()
					.asString()
					.toLowerCase(Locale.ROOT);

			if (KMPSearch.search(nameHaystack, needle) == 1) {
				result.add(customer);
			}
			else if (KMPSearch.search(emailHaystack, needle) == 1) {
				result.add(customer);
			}
		}
		return result;
	}
}