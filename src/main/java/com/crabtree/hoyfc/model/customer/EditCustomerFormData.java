package com.crabtree.hoyfc.model.customer;

import lombok.Data;

@Data
public class EditCustomerFormData extends CreateCustomerFormData {

	private String id;

	public static EditCustomerFormData fromCustomer(Customer customer) {
		EditCustomerFormData result = new EditCustomerFormData();

		result.setFirstName(customer.getCustomerName()
				.getFirstName());

		result.setLastName(customer.getCustomerName()
				.getLastName());

		result.setGender(customer.getGender());

		result.setBirthday(customer.getBirthday());

		result.setEmail(customer.getEmail()
				.asString());

		result.setPhoneNumber(customer.getPhoneNumber()
				.asString());

		return result;
	}

	public EditCustomerParameters toParameters() {
		return new EditCustomerParameters(
				new CustomerName(getFirstName(), getLastName()),
				getGender(),
				new Email(getEmail()),
				getBirthday(),
				new PhoneNumber(getPhoneNumber()));
	}
}