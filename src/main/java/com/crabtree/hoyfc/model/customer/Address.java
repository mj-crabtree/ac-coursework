package com.crabtree.hoyfc.model.customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {
	private String buildingNumber;
	private String firstLine;
	private String townCity;
	private String postalCode;
	private String country;
}