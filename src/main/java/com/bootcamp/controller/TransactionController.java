package com.bootcamp.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.entity.BankAccountDto;
import com.bootcamp.entity.Transaction;
import com.bootcamp.service.TransactionService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
		
	//Deposit  
	@PostMapping("/deposit/{dniCustomer}/{accountNumber}/{amount}")
	public Mono<BankAccountDto> addDeposit (@PathVariable String dniCustomer,@PathVariable String accountNumber ,@PathVariable double amount){		
		return service.deposit(dniCustomer,accountNumber,amount);
	}
	
	//Withdrawal 
	@PostMapping("/withdrawal/{dniCustomer}/{accountNumber}/{amount}")
	public Mono<BankAccountDto> withdrawaltBankAccount (@PathVariable String dniCustomer,@PathVariable String accountNumber ,@PathVariable double amount){		
		return service.withdrawal(dniCustomer,accountNumber,amount);
	}
	
	//CRUD
	@PostMapping("/addBank")
	public Mono<Transaction> save	(@Valid @RequestBody Transaction t)	{
		return service.save(t);
	}
	
	@PutMapping("/updateBank")
	public Mono<Void> update		(@Valid @RequestBody Transaction t){
		return service.update(t);
	}
	
	@DeleteMapping( "/{id}")
	public Mono<Void> delete		(@PathVariable String id){
		return service.delete(id);
	}

	@GetMapping("/getAllTransaction")
	public Flux<Transaction> getAllTransaction(){
		return service.getAll();
	}
	

	
	
		
}
