package service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import Dto.AccountDto;
import Dto.CardDto;
import entity.Card;
import repository.AccountRepo;
import repository.CardRepo;


@Service
public class CardService {
	private final CardRepo cardRepo;
	private final AccountRepo accountRepo;
    
    
    public CardService(CardRepo cardRepo, AccountRepo accountRepo) {
        this.cardRepo = cardRepo;
        this.accountRepo = accountRepo;
        
    }
    
    public Card create(Card card){
    	return cardRepo.save(card);
    }
    
    public Optional<Card> get(int id) {
        return cardRepo.findById(id);
    }
    
    public int getDebt(int id) {
    	return cardRepo.findById(id).get().getDebt();
    }
    
    public CardDto updateDebt(int id,int money)throws Exception{
    	CardDto cardDto = cardRepo.findById(id)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card is not found!")).cardDto();
    	if(cardDto.getBoundary()>cardDto.getDebt()) {
    		cardDto.setDebt(cardDto.getDebt()+money);
    		return cardRepo.save(cardDto.toCard()).cardDto();
    	}else {
    		throw new Exception("Limit not enough");
    	}
    }
    
    public CardDto payDebt(int id,int accountId,int debt)throws Exception {
    	AccountDto accountDto = accountRepo.findById(accountId)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account is not found!")).accountDto();
    	int balance = accountDto.getBalance();
    	
    	CardDto cardDto = cardRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Card is not found!")).cardDto();
    	
    	if(balance>debt && debt !=0) {
    		accountDto.setBalance(accountDto.getBalance()-debt);
    		cardDto.setDebt(cardDto.getDebt()-debt);
    	}else {
    		throw new Exception("You don't have any debt!!!..");
    	}
    	return cardRepo.save(cardDto.toCard()).cardDto();
    	
    }
	

}
