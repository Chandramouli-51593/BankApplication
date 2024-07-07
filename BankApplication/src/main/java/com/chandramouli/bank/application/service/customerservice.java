package com.chandramouli.bank.application.service;
import com.chandramouli.bank.application.entity.customer;
import com.chandramouli.bank.application.repository.CustomerRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class customerservice {
	@Autowired
	CustomerRepository customerrepository;
	
	public customer CreateAccount(customer Customer) {
		return customerrepository.save(Customer);
	}
	
	public Optional<customer> GetAccount(long id){
		return customerrepository.findById(id);
	}
	
	public customer Deposit(long amount,long id) {
		customer c=GetAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
		c.setBalance(c.getBalance()+amount);
		customerrepository.AddBalancebyAccno(c.getBalance()+amount,id);
		return c;
	}
	
	public customer Withdraw(long amount,long id) {
		customer c=GetAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
		if(amount>c.getBalance()) {
			throw new RuntimeException("Insufficient funds");
		}
		c.setBalance(c.getBalance()-amount);
		customerrepository.WithdrawbyAccno(c.getBalance()-amount,id);
		return c;
	}
	public long getBalance(int id) {
		return customerrepository.FindBalancebyAccno(id);
	}
	public void transfer(long sendid,long receiveid,long amount) {
		customerrepository.AddBalancebyAccno(amount,receiveid);
		customerrepository.WithdrawbyAccno(amount,sendid);
	}

}
