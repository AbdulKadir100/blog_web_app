package service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Dto.AccountDto;
import entity.Account;
import enums.AccountType;
import exchange.Exchange;
import exchange.RestExchangeDto;
import repository.AccountRepo;


@Service
public class AccountService {
    private final AccountRepo accountRepo;
    private static AccountType accountType;
    
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
        
    }
    
    //Creating a particular account
    public Account createAccount(Account acct) {
    	return accountRepo.save(acct);
    }
    
    //Deleting a account
    public Account delete(int id){
        return accountRepo.deleteAccountById(id);
    }

    //Get All Accounts
    public List<Account> getAllAccount(){
    	return accountRepo.findAll();
    }
    //Get summary of an account
    public Account getAccountSummary(int id) {
        /*Optional<Account> account=accountRepo.findById(id);
        if (!account.isPresent()){
            System.out.println("Account not found!");
        }*/

        /*AccountDto accountDto=new AccountDto();
        accountDto.setCurrency(account.get().getCurrency());
        accountDto.setBalance(accountDto.getBalance());
        return accountDto;*/
        return accountRepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account " +
                "not found with id"+id));
    }
    
    public AccountDto transferMoney(String IBAN, String toIBAN, int moneyTransfer){

    	AccountDto demandDepositAccountDto = accountRepo.getByIban(IBAN)
    			.orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found")).accountDto();
    	
    	accountType=demandDepositAccountDto.getAccountType();
    	if(accountType==AccountType.DEMAND_DEPOSIT_ACCOUNT) {
    		int demandDepositMoney = demandDepositAccountDto.getBalance();
    		AccountDto accountDto = accountRepo.getByIban(toIBAN)
    				.orElseThrow(() ->   new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found"))
    				.accountDto();
    		int money = accountDto.getBalance(); 
    		
    		if(accountDto.getAccountType()==AccountType.SAVINGS_ACCOUNT) {
    			return moneyTransfer(moneyTransfer, demandDepositAccountDto, demandDepositMoney, accountDto, money);
    		}else {
    			return moneyTransfer(moneyTransfer, demandDepositAccountDto, demandDepositMoney, accountDto, money);
    		}
    	}else {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Account is not found!");
    	}
    	
    	
    }
    
    private AccountDto moneyTransfer(int moneyTransfer,AccountDto demandDepositAccountDto,int demandDepositMoney,AccountDto accountDto,int money) {

    	if(Math.abs(demandDepositMoney-moneyTransfer)<0) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not enough money in your demand deposit account!");
    	}else {
    		if(demandDepositAccountDto.getCurrency().equals(accountDto.getCurrency())) {
    			demandDepositAccountDto.setBalance(demandDepositMoney-moneyTransfer);
    			accountDto.setBalance(money+moneyTransfer);
    		}else {
    			RestExchangeDto exchangeDto =
                        Exchange.convertedCurrency.apply(demandDepositAccountDto.getCurrency());
                demandDepositAccountDto.setBalance(demandDepositMoney - moneyTransfer);
                accountDto.setBalance((int) (money + (moneyTransfer * exchangeDto.getRates().get(accountDto.getCurrency()))));
    		}
    		accountRepo.save(accountDto.toAccount()).accountDto();
    		return accountRepo.save(demandDepositAccountDto.toAccount()).accountDto();
    	}
    }
}
