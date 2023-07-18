package Dto;

import java.time.LocalDate;

import entity.Account;
import enums.AccountType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class AccountDto {
	    private int id;
	    private String accountNumber;
	    private int balance;
	    private LocalDate createdDate;
	    private LocalDate modifiedDate;
	    private String iban;
	    private String currency;
	    private AccountType accountType;

	    public AccountDto() {

	    }
	    public AccountDto(int id, String accountNumber, int balance, LocalDate createdDate, LocalDate modifiedDate, String iban, String currency, AccountType accountType) {
	        this.id = id;
	        this.accountNumber = accountNumber;
	        this.balance = balance;
	        this.createdDate = createdDate;
	        this.modifiedDate = modifiedDate;
	        this.iban = iban;
	        this.currency = currency;
	        this.accountType = accountType;
	    }  

	    public Account toAccount(){
	        return Account.builder()
	                .id(this.id)
	                .accountNumber(this.accountNumber)
	                .balance(this.balance)
	                .createdDate(this.createdDate)
	                .modifiedDate(this.modifiedDate)
	                .iban(this.iban)
	                .currency(this.currency)
	                .accountType(this.accountType)
	                .build();
	    }
		public AccountType getAccountType() {
			return accountType;
		}
		public int getBalance() {
			return balance;
		}
		public String getCurrency() {
			return currency;
		}
		public void setBalance(int i) {
			this.balance=i;
			
		}
		

}
