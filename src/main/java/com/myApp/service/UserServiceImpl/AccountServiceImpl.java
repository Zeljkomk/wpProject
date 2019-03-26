package com.myApp.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myApp.Dao.PrimaryAccountDao;
import com.myApp.Dao.SavingsAccountDao;
import com.myApp.domain.PrimaryAccount;
import com.myApp.domain.PrimaryTransaction;
import com.myApp.domain.SavingsAccount;
import com.myApp.domain.SavingsTransaction;
import com.myApp.domain.User;
import com.myApp.service.AccountService;
import com.myApp.service.TransactionService;
import com.myApp.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 11223145;
	
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		// TODO Auto-generated method stub
		PrimaryAccount primaryAccount = new PrimaryAccount();
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		// TODO Auto-generated method stub
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingsAccount);
		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	
	}
	
	private int accountGen() {
		return ++nextAccountNumber;
	}
	
	public void deposit(String accountType,double amount,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Deposit to Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.savePrimaryDepositTransaction(primaryTransaction);
		
		} else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Deposit to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);			
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		}
	}
	
	public void withdraw(String accountType,double amount,Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date,"Withdraw to Primary Account","Account","Finished",amount,primaryAccount.getAccountBalance(),primaryAccount);
			transactionService.savePrimaryWithdrawTransaction(primaryTransaction);
		
		} else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date,"Withdraw to Savings Account","Account","Finished",amount,savingsAccount.getAccountBalance(),savingsAccount);			
			transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
		}
	}
	


}
