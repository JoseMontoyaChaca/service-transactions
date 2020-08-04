package com.bootcamp.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.bootcamp.entity.BankAccountDto;

public interface BankAccountDtoRepository extends ReactiveMongoRepository<BankAccountDto, String > {

}
