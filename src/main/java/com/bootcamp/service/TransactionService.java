package com.bootcamp.service;

import java.util.Objects;
import java.util.UUID;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.bootcamp.entity.BankAccountDto;
import com.bootcamp.entity.Transaction;
import com.bootcamp.repository.BankAccountDtoRepository;
import com.bootcamp.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

	
	
	private WebClient bankAccount = WebClient.create("htpp://localhost:8072/bankAccount");

	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	BankAccountDtoRepository bankAccountRepo;
	
	
	public Flux<Transaction> getAll() {
		return repository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Transaction> findById(final String id) {
		return repository.findById(id);
	}
	
	
	public Mono<Void> update(Transaction transaction) {
		return findById(transaction.getNumberTransaction())
				.flatMap(repository::save)
				.thenEmpty(Mono.empty());
	}
	
	
	public Mono<BankAccountDto> deposit (String dniCustomer, String accountNumber , double amount){
		 Mono<BankAccountDto>  mono = bankAccount.post()
			.uri("/deposit/{dniCustomer}/{accountNumber}/{amount}",dniCustomer,accountNumber,amount)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(BankAccountDto.class);
		 	return mono;	 	
	}
	
	
	
	

	public Mono<Transaction> save(Transaction t, double amount) {
			return 	repository.save(t)
					.map(document -> {
					document.setNumberTransaction(UUID.randomUUID().toString());
					document.setBalance(document.getBalance() + amount);
					return document;
				});	
	
	}
	

	public Mono<Void> updateBalance(String	dniCustomer,String accountNumber, double amount){
		Mono<Transaction> mono = findByAccountNumber(accountNumber)
				.switchIfEmpty(Mono.error(new NotFound()))
				.map(document -> {
					document.setNumberTransaction(UUID.randomUUID().toString());
					document.setDniCustomer(dniCustomer);
					document.setBalance(document.getBalance() + amount);
					document.setAccountNumber(accountNumber);
					return document;
				});
		return repository.saveAll(mono).then();					
	}	
		
	public Mono<Transaction> delete(final String id) {
		final Mono<Transaction> dbTransac = findById(id);
		if (Objects.isNull(dbTransac)) {
			return Mono.empty();
		}
		return findById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(transacToBeDeleted -> repository
				.delete(transacToBeDeleted).then(Mono.just(transacToBeDeleted)));
	}
	
	public Mono<Transaction> findByAccountNumber(String accountNumber){
		
		
		Flux<Transaction> flux =repository.findAll().filter(p -> p.getAccountNumber().equals(accountNumber));
		return Mono.from(flux);
	}
	
}
