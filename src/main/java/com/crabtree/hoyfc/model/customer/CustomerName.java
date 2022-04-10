package com.crabtree.hoyfc.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerName {
	private String firstName;
	private String lastName;

	protected CustomerName() {
	}

	public String getFullName() {
		return String.format("%s %s", firstName, lastName);
	}
}