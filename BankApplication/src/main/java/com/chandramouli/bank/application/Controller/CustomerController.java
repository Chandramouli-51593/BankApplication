package com.chandramouli.bank.application.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chandramouli.bank.application.entity.customer;
import com.chandramouli.bank.application.service.customerservice;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	@Autowired
	customerservice customerService;
	
	@PostMapping
	public customer CreateAccount(@RequestBody customer c) {
		return customerService.CreateAccount(c);
	}
	@GetMapping("/{id}")
	public customer GetAccount(@PathVariable long id) {
		return customerService.GetAccount(id). orElseThrow(() -> new RuntimeException("Account not found"));
				
	}
	
	@PostMapping("/{id}/deposit")
	public customer Deposit(@PathVariable long id,@RequestBody Map<String ,Long>request) {
		long amount=request.get("amount");
		return customerService.Deposit(amount,id);
	}
	@PostMapping("/{id}/withdraw")
	public customer Withdraw(@PathVariable long id,@RequestBody Map<String ,Long>request) {
		long amount=request.get("amount");
		return customerService.Withdraw(amount, id);
	}
	
	@GetMapping("/{acctID}/balance")
	public long getBalance(@PathVariable int acctID) {
		return customerService.getBalance(acctID);
	}
	
	@PostMapping("/{id}/{sendid}/transfer/{receiveid}/{amount}")
	public void transfer(@PathVariable long sendid,@PathVariable long receiveid,@PathVariable long amount) {
		customerService.transfer(sendid, receiveid, amount);
	}
	
}

