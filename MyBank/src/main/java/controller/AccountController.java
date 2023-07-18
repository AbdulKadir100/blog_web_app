package controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Dto.AccountDto;
import entity.Account;
import jakarta.validation.Valid;
import service.AccountService;

@Validated
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	private final AccountService accountService;
	
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDto create(@Valid @RequestBody AccountDto accountDto) {
		return accountService.createAccount(accountDto.toAccount()).accountDto();
		
	}
	
	@GetMapping("/{id}")
	public AccountDto getAccountSummary(@PathVariable ("id")Integer id) {
		return accountService.getAccountSummary(id).accountDto();
	}
	
	@GetMapping("/getAll")
	public List<Account> getAll(){
		return accountService.getAllAccount();
	}
	
	@PutMapping("/{IBAN}/{toIBAN}")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDto transferMoney(@PathVariable("IBAN") String IBAN,
            @PathVariable("toIBAN") String toIBAN,
            @RequestParam("moneyTransfer")int moneyTransfer) {
		
		return accountService.transferMoney(IBAN, toIBAN, moneyTransfer);
		
	}
	
	@DeleteMapping("{/id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto delete(@PathVariable("id")Integer id){
        return accountService.delete(id).accountDto();
    }
	
	
	
}
