package com.crabtree.hoyfc.repository;

import com.crabtree.customDSA.algorithms.search.KMPSearch.KMPSearchImpl;
import com.crabtree.customDSA.algorithms.sort.InsertionSort.InsertionSort;
import com.crabtree.customDSA.dataStructures.dynamicArrayList.DynamicArrayList;
import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.model.customer.comparatorFactory.CustomerComparatorFactory;
import com.crabtree.hoyfc.service.pagination.Pagination;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomerRepository {
	private DynamicArrayList<Customer> customers;

	public CustomerRepository() {
		this.customers = new DynamicArrayList<>();
	}

	public Customer getById(int id) {
		return customers.get(id);
	}

	public Customer save(Customer customer) {
		return customers.add(customer);
	}

	public DynamicArrayList<Customer> getCustomers() {
		return customers;
	}

	public Integer count() {
		return this.customers.count();
	}

	public DynamicArrayList<Customer> getSortedPaginatedCustomers(int pageNumber, int pageSize, String sortColumn, String sortDirection) {
		var comparator = CustomerComparatorFactory.getComparator(sortColumn, sortDirection);

		var is = new InsertionSort();
		is.sort(customers, comparator);

		return (DynamicArrayList<Customer>) Pagination.paginateCollection(this.customers, pageNumber, pageSize);
	}

	public DynamicArrayList<Customer> findCustomers(String needle) {
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

			if (KMPSearchImpl.knuthMorrisPratt(nameHaystack, needle) == 1) {
				result.add(customer);
			}
			else if (KMPSearchImpl.knuthMorrisPratt(emailHaystack, needle) == 1) {
				result.add(customer);
			}
		}
		return result;
	}
}