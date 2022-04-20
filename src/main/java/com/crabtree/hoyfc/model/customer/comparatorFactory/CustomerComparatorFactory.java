package com.crabtree.hoyfc.model.customer.comparatorFactory;

import com.crabtree.hoyfc.model.customer.Customer;
import com.crabtree.hoyfc.service.pageSort.SortDirection;

import java.util.Comparator;

public class CustomerComparatorFactory {

	public static Comparator<Customer> getComparator(String sortColumn, SortDirection sortDirection) {
		switch (sortColumn) {
			case "Id":
				return getIdComparator(sortDirection);
			case "FirstName":
				return getFirstNameComparator(sortDirection);
			case "LastName":
				return getLastNameComparator(sortDirection);
			case "Email":
				return getEmailComparator(sortDirection);
			case "Birthday":
				return getBirthdayComparator(sortDirection);
			default:
				throw new IllegalStateException("Unexpected value: " + sortColumn);
		}
	}

	private static Comparator<Customer> getBirthdayComparator(SortDirection direction) {
		return direction
				.getDirection()
				.equals("ASC") ? BirthdayComparator.ascending() : BirthdayComparator.descending();
	}

	private static Comparator<Customer> getEmailComparator(SortDirection direction) {
		return direction
				.getDirection()
				.equals("ASC") ? EmailComparator.ascending() : EmailComparator.descending();
	}

	private static Comparator<Customer> getLastNameComparator(SortDirection direction) {
		return direction
				.getDirection()
				.equals("ASC") ? LastNameComparator.ascending() : LastNameComparator.descending();
	}

	private static Comparator<Customer> getFirstNameComparator(SortDirection direction) {
		return direction
				.getDirection()
				.equals("ASC") ? FirstNameComparator.ascending() : FirstNameComparator.descending();
	}

	private static Comparator<Customer> getIdComparator(SortDirection direction) {
		return direction
				.getDirection()
				.equals("ASC") ? IdComparator.ascending() : IdComparator.descending();
	}

	private static class BirthdayComparator {
		public static Comparator<Customer> descending() {
			return (o1, o2) -> o1
					.getBirthday()
					.compareTo(o2.getBirthday());
		}

		public static Comparator<Customer> ascending() {
			return (o1, o2) -> o2
					.getBirthday()
					.compareTo(o1.getBirthday());
		}
	}

	private static class EmailComparator {
		public static Comparator<Customer> descending() {
			return (o1, o2) -> o1
					.getEmail()
					.asString()
					.compareTo(o2
							.getEmail()
							.asString());
		}

		public static Comparator<Customer> ascending() {
			return (o1, o2) -> o2
					.getEmail()
					.asString()
					.compareTo(o1
							.getEmail()
							.asString());
		}
	}

	private static class LastNameComparator {
		public static Comparator<Customer> ascending() {
			return (o1, o2) -> o1
					.getCustomerName()
					.getLastName()
					.compareTo(o2
							.getCustomerName()
							.getLastName());
		}

		public static Comparator<Customer> descending() {
			return (o1, o2) -> o2
					.getCustomerName()
					.getLastName()
					.compareTo(o1
							.getCustomerName()
							.getLastName());
		}
	}

	private static class FirstNameComparator {
		public static Comparator<Customer> ascending() {
			return (o1, o2) -> o1
					.getCustomerName()
					.getFirstName()
					.compareTo(o2
							.getCustomerName()
							.getFirstName());
		}

		public static Comparator<Customer> descending() {
			return (o1, o2) -> o2
					.getCustomerName()
					.getFirstName()
					.compareTo(o1
							.getCustomerName()
							.getFirstName());
		}
	}

	private static class IdComparator {
		public static Comparator<Customer> ascending() {
			return (o1, o2) -> o1
					.getId()
					.compareTo(o2.getId());
		}

		public static Comparator<Customer> descending() {
			return (o1, o2) -> o2
					.getId()
					.compareTo(o1.getId());
		}
	}
}