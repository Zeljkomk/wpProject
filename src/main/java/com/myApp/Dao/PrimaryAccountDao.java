package com.myApp.Dao;

import org.springframework.data.repository.CrudRepository;

import com.myApp.domain.PrimaryAccount;

public interface PrimaryAccountDao extends CrudRepository<PrimaryAccount,Long>{

	PrimaryAccount findByAccountNumber(int accountNumber);
}
