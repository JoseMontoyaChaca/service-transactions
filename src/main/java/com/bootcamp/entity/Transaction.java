package com.bootcamp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Transaction {
	@Id
	private Integer numberTransaction;
	private String dniCustomer;
	private double balance;
	private TypeTransaction typeTransaction;
	private Long  cardNumber;
	private Long accountNumber;
	private String dateTransaction; 

}
