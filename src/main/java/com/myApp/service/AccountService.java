package com.myApp.service;

import java.security.Principal;

import com.myApp.domain.PrimaryAccount;
import com.myApp.domain.SavingsAccount;

public interface AccountService {

	PrimaryAccount createPrimaryAccount();
	SavingsAccount createSavingsAccount();
	void deposit(String accountType,double amount,Principal principal);
	void withdraw(String accountType,double amount,Principal principal);
}
