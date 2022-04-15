package com.crabtree.hoyfc.model.customer;

import com.crabtree.hoyfc.model.baseEntity.BaseEntity;
import com.crabtree.hoyfc.model.order.CustomerOrder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class Customer extends BaseEntity {
	private CustomerName customerName;
	// todo: customer address
	// private CustomerAddress customerAddress;
	private PhoneNumber phoneNumber;
	private Email email;
	private Gender gender;
	private LocalDate birthday;
	private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedAt;
	private List<CustomerOrder> orders;
	private boolean active = true;
}