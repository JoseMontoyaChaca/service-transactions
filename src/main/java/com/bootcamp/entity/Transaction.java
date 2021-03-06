package com.bootcamp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document
public class Transaction {
	@Id
	private String numberTransaction;
	private String dniCustomer;
	private double balance;
	private TypeTransaction typeTransaction;
	private String accountNumber;
	private String dateTransaction;
	

}
