package com.crabtree.hoyfc.model.customer.comparatorFactory;

import com.crabtree.hoyfc.model.customer.Customer;

import java.util.Comparator;

public class CustomerComparatorFactory {

	// todo: make this not generic
	public static <T extends Customer> Comparator<T> getComparator(String sortColumn, String sortDirection) {
		switch (sortColumn) {
			case "id":
				return getIdComparator(sortDirection);
			case "firstName":
				return getFirstNameComparator(sortDirection);
			case "lastName":
				return getLastNameComparator(sortDirection);
			case "email":
				return getEmailComparator(sortDirection);
			case "birthday":
				return getBirthdayComparator(sortDirection);
			default:
				throw new IllegalStateException("Unexpected value: " + sortColumn);
		}
	}

	private static <T extends Customer> Comparator<T> getBirthdayComparator(String direction) {
		return direction.equals("asc") ? BirthdayComparator.ascending() : BirthdayComparator.descending();
	}

	private static <T extends Customer> Comparator<T> getEmailComparator(String direction) {
		return direction.equals("asc") ? EmailComparator.ascending() : EmailComparator.descending();
	}

	private static <T extends Customer> Comparator<T> getLastNameComparator(String direction) {
		return direction.equals("asc") ? LastNameComparator.ascending() : LastNameComparator.descending();
	}

	private static <T extends Customer> Comparator<T> getFirstNameComparator(String direction) {
		return direction.equals("asc") ? FirstNameComparator.ascending() : FirstNameComparator.descending();
	}

	private static <T extends Customer> Comparator<T> getIdComparator(String direction) {
		return direction.equals("asc") ? IdComparator.ascending() : IdComparator.descending();
	}

	private static class BirthdayComparator {
		public static <T extends Customer> Comparator<T> descending() {
			return (o1, o2) -> o1
					.getBirthday()
					.compareTo(o2.getBirthday());
		}

		public static <T extends Customer> Comparator<T> ascending() {
			return (o1, o2) -> o2
					.getBirthday()
					.compareTo(o1.getBirthday());
		}
	}

	private static class EmailComparator {
		public static <T extends Customer> Comparator<T> descending() {
			return (o1, o2) -> o1
					.getEmail()
					.asString()
					.compareTo(o2
							.getEmail()
							.asString());
		}

		public static <T extends Customer> Comparator<T> ascending() {
			return (o1, o2) -> o2
					.getEmail()
					.asString()
					.compareTo(o1
							.getEmail()
							.asString());
		}
	}

	private static class LastNameComparator {
		public static <T extends Customer> Comparator<T> ascending() {
			return (o1, o2) -> o1
					.getCustomerName()
					.getLastName()
					.compareTo(o2
							.getCustomerName()
							.getLastName());
		}

		public static <T extends Customer> Comparator<T> descending() {
			return (o1, o2) -> o2
					.getCustomerName()
					.getLastName()
					.compareTo(o1
							.getCustomerName()
							.getLastName());
		}
	}

	private static class FirstNameComparator {
		public static <T extends Customer> Comparator<T> ascending() {
			return (o1, o2) -> o1
					.getCustomerName()
					.getFirstName()
					.compareTo(o2
							.getCustomerName()
							.getFirstName());
		}

		public static <T extends Customer> Comparator<T> descending() {
			return (o1, o2) -> o2
					.getCustomerName()
					.getFirstName()
					.compareTo(o1
							.getCustomerName()
							.getFirstName());
		}
	}

	private static class IdComparator {
		public static <T extends Customer> Comparator<T> ascending() {
			return (o1, o2) -> o1
					.getId()
					.compareTo(o2.getId());
		}

		public static <T extends Customer> Comparator<T> descending() {
			return (o1, o2) -> o2
					.getId()
					.compareTo(o1.getId());
		}
	}
}