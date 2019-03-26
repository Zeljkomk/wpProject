package com.myApp.Dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.myApp.domain.PrimaryTransaction;

public interface PrimaryTransactionDao extends CrudRepository<PrimaryTransaction,Long> {

	List<PrimaryTransaction> findAll();
}
