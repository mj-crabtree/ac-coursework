package com.crabtree.hoyfc.model.customer;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class Email {
	private String email;

	protected Email() {
	}

	public Email(String email) {
		Assert.hasText(email, "email cannot be blank");
		Assert.isTrue(email.contains("@"), "email must contain an '@' symbol");
		this.email = email;
	}

	public String asString() {
		return this.email.toLowerCase();
	}

	@Override
	public String toString() {
		return "Email{" +
				"email='" + email + '\'' +
				", asString='" + asString() + '\'' +
				'}';
	}
}