package com.bootcamp.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class BankAccountDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;	
	private String nameAccount;
	private String accountNumber;
	private String dniCustomer;
	private double balance;
	private String idBank;
		
	
}
