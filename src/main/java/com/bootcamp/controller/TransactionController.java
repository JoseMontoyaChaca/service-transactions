package com.bootcamp.controller;

import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.entity.Transaction;
import com.bootcamp.service.TransactionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(value = "/transaction")
public class TransactionController {
	

	@Autowired
	private TransactionService service;
	
	@GetMapping("/getAllTransaction")
	public Flux<Transaction> getAllTransaction(){
		return service.getAll();
	}
	
	
	
//	
//	@PostMapping("/deposito/{monto}")
//	public Mono<Object> addDeposito (@RequestBody Transaction transaction,@PathVariable double monto ) {
//			Mono m = service.save(transaction)
//				.flatMap(p -> p.setBalance(p.getBalance() + monto));
//			return p;
//	}
//	
	
	
//	@PostMapping("/addTransaction/{amount}")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Mono<Transaction> saveCustomer(@PathVariable(name = "amount") Double amount,@RequestBody Transaction t) {
//		t.setNumberTransaction(UUID.randomUUID().toString());
//			Mono<Object> mono =  service.save(t)
//			
//	}
	
	

}
