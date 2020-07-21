package com.bootcamp.service;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import com.bootcamp.entity.Transaction;
import com.bootcamp.repository.TransactionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	
	public Flux<Transaction> getAll() {
		return repository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Transaction> findById(final Integer id) {
		return repository.findById(id);
	}
	
	
	public Mono<Void> update(Transaction transaction) {
		return findById(transaction.getNumberTransaction())
				.flatMap(repository::save)
				.thenEmpty(Mono.empty());
	}

	
	public Mono<Transaction> save(@RequestBody Transaction t) {
		return repository.save(t);
	}
	
	
	public Mono<Transaction> delete(final Integer id) {
		final Mono<Transaction> dbTransac = findById(id);
		if (Objects.isNull(dbTransac)) {
			return Mono.empty();
		}
		return findById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(transacToBeDeleted -> repository
				.delete(transacToBeDeleted).then(Mono.just(transacToBeDeleted)));
	}
	
	
	
}
