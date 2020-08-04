package com.bootcamp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.entity.BankAccountDto;
import com.bootcamp.repository.BankAccountDtoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountDtoSerivice {
	
	@Autowired
	BankAccountDtoRepository repository;
	
	public Flux<BankAccountDto> findAll(){
		return repository.findAll();
	}
	
	public Mono<BankAccountDto> save( BankAccountDto b){
		return repository.save(b);
	}
		
}
