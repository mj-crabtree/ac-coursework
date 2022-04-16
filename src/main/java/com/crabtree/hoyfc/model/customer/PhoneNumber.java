package com.crabtree.hoyfc.model.customer;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class PhoneNumber {
	private String phoneNumber;

	protected PhoneNumber() {
	}

	public PhoneNumber(String phoneNumber) {
		Assert.hasText(phoneNumber, "phone number cannot be blank");
		this.phoneNumber = phoneNumber;
	}

	public String asString() {
		return phoneNumber;
	}

	@Override
	public String toString() {
		return "PhoneNumber{" +
				"phoneNumber='" + phoneNumber + '\'' +
				", asString='" + asString() + '\'' +
				'}';
	}
}