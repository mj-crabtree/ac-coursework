package com.crabtree.hoyfc.model.customer.createCustomer;

import com.crabtree.hoyfc.model.customer.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateCustomerParameters {
	private final CustomerName userName;
	private final Address customerAddress;
	private final Gender gender;
	private final Email email;
	private final LocalDate birthday;
	private final PhoneNumber phoneNumber;
}