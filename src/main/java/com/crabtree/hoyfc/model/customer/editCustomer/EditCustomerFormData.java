package com.crabtree.hoyfc.model.customer.editCustomer;

import com.crabtree.hoyfc.model.customer.*;
import com.crabtree.hoyfc.model.customer.createCustomer.CreateCustomerFormData;
import lombok.Data;

@Data
public class EditCustomerFormData extends CreateCustomerFormData {

	private String id;

	public static EditCustomerFormData fromCustomer(Customer customer) {
		EditCustomerFormData result = new EditCustomerFormData();

		result.setFirstName(customer
				.getCustomerName()
				.getFirstName());

		result.setLastName(customer
				.getCustomerName()
				.getLastName());

		result.setGender(customer.getGender());

		result.setBirthday(customer.getBirthday());

		result.setEmail(customer
				.getEmail()
				.asString());

		result.setPhoneNumber(customer
				.getPhoneNumber()
				.asString());

		result.setBuildingNumber(customer
				.getAddress()
				.getBuildingNumber());

		result.setFirstLine(customer
				.getAddress()
				.getFirstLine());

		result.setTownCity(customer
				.getAddress()
				.getTownCity());

		result.setPostalCode(customer
				.getAddress()
				.getPostalCode());

		result.setCountry(customer
				.getAddress()
				.getCountry());

		return result;
	}

	public EditCustomerParameters toParameters() {
		return new EditCustomerParameters(
				new Address(getBuildingNumber(), getFirstLine(), getTownCity(), getPostalCode(), getCountry()),
				new CustomerName(getFirstName(), getLastName()),
				getGender(),
				new Email(getEmail()),
				getBirthday(),
				new PhoneNumber(getPhoneNumber()));
	}
}