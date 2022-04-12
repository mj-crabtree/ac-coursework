package com.crabtree.hoyfc.model.customer.createCustomer;

import com.crabtree.hoyfc.model.customer.CustomerName;
import com.crabtree.hoyfc.model.customer.Email;
import com.crabtree.hoyfc.model.customer.Gender;
import com.crabtree.hoyfc.model.customer.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateCustomerParameters {
	private final CustomerName userName;
	private final Gender gender;
	private final Email email;
	private final LocalDate birthday;
	private final PhoneNumber phoneNumber;
}