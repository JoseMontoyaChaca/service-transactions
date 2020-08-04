package com.bootcamp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.entity.BankAccountDto;
import com.bootcamp.service.BankAccountDtoSerivice;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/bankAccountDto")
public class BankAccountDtoController {
	
	
	@Autowired
	BankAccountDtoSerivice service;
	
	@GetMapping("/findAll")
	public Flux<BankAccountDto> getAllBankAccount(){
		return service.findAll();
	}
	
	
	
	
	
	
	

}
