package com.crabtree.hoyfc.model.customer.comparator;



import com.crabtree.hoyfc.model.customer.Customer;

import java.util.Comparator;

public class LastNameComparator implements Comparator {
	@Override
	public int compare(Object o1, Object o2) {
			var key = (String) o1;
			var comparison = (Customer) o2;

			return key.compareTo(comparison.getCustomerName().getLastName());
	}

	public int compare(Object o1, Object o2, boolean ascending) {
		var key = (String) o1;
		var comparison = (Customer) o2;

		if (ascending) {
			return key.compareTo(comparison
					.getCustomerName()
					.getLastName());
		}
		else {
			return comparison
					.getCustomerName()
					.getLastName()
					.compareTo(key);
		}
	}
}