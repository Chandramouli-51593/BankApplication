package com.chandramouli.bank.application.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chandramouli.bank.application.entity.customer;

import jakarta.transaction.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<customer,Long>{
	Optional<customer> findById(long id);
	
	@Query("select balance from customer where number=?1")
	public long FindBalancebyAccno(long number);
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update customer set balance=balance+?1 where number=?2")
	public void AddBalancebyAccno(long balance,long number);
	
	@Transactional
	@Modifying(clearAutomatically=true)
	@Query("update customer set balance=balance-?1 where number=?2")
	public void WithdrawbyAccno(long balance,long number);
	
	

}
