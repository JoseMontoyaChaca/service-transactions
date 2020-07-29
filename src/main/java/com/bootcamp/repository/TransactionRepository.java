package com.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.entity.Transaction;

public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

}
