package com.bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.bootcamp.entity.BankAccountDto;
import com.bootcamp.entity.Transaction;
import com.bootcamp.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {
	
	private WebClient bankAccount = WebClient.create("htpp://localhost:8072/bankAccount");
	
	@Autowired
	private TransactionRepository repository;
		
	//Deposit
	public Mono<BankAccountDto> deposit (String dniCustomer, String accountNumber , double amount){
		 Mono<BankAccountDto>  mono = bankAccount.post()
			.uri("/deposit/{dniCustomer}/{accountNumber}/{amount}",dniCustomer,accountNumber,amount)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(BankAccountDto.class);
		 	return mono;	
	}
	
	//Deposit
	public Mono<BankAccountDto> withdrawal (String dniCustomer, String accountNumber , double amount){
		 Mono<BankAccountDto>  mono = bankAccount.post()
			.uri("/withdrawal/{dniCustomer}/{accountNumber}/{amount}",dniCustomer,accountNumber,amount)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(BankAccountDto.class);
		 	return mono;	
	}
	
	
	//CRUD
	public Mono<Transaction> save(Transaction t) {
		return 	repository.save(t);
}
	
	public Mono<Void> update(Transaction transaction) {
		return findById(transaction.getNumberTransaction())
				.flatMap(repository::save)
				.thenEmpty(Mono.empty());
	}
			
	public Mono<Void> delete(final String id) {
		return findById(id)
				.flatMap(repository::delete);
	}
	
	public Flux<Transaction> getAll() {
		return repository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Transaction> findById(final String id) {
		return repository.findById(id);
	}
	
	public Mono<Transaction> findByAccountNumber(String accountNumber){
		Flux<Transaction> flux =repository.findAll().filter(p -> p.getAccountNumber().equals(accountNumber));
		return Mono.from(flux);
	}
	
}
