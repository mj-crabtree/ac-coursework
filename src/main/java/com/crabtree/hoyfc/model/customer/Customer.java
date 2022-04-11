package com.crabtree.hoyfc.model.customer;

import com.crabtree.hoyfc.model.BaseEntity;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
public class Customer extends BaseEntity {
	private CustomerName customerName;
	private PhoneNumber phoneNumber;
	private Email email;
	private Gender gender;
	private LocalDate birthday;
	private LocalDateTime createdOn = LocalDateTime.now();
	private LocalDateTime updatedAt;
	private boolean active = true;
}