  package controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import Dto.CardDto;
import lombok.SneakyThrows;
import service.CardService;

@RestController
@RequestMapping("/api/cards")
public class CardController {
	
	public final CardService cardService;
	
	public CardController(CardService cardService) {
		this.cardService = cardService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CardDto create(@RequestBody CardDto cardDto) {
		return cardService.create(cardDto.toCard()).cardDto();
		
	}
	
	@GetMapping("/{id}")
	public CardDto getCard(@PathVariable("id") Integer id){
	        return cardService.get(id)
	                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Card not found!")).cardDto();
	    }
	
	@GetMapping("/{id}/getdebt")
	public int getDebt(@PathVariable("id")Integer id) {
		return cardService.getDebt(id);		
	}
	
	@SneakyThrows
	@PutMapping("/{id}/updatedebt")
	public CardDto updateDebt(@PathVariable("id")Integer id,@RequestParam("money")Integer money) throws Exception {
		return cardService.updateDebt(id, money);
	}
	
	@PutMapping("/{id}/paydebt/{accountID}")
    public CardDto payDebt(@PathVariable("id") Integer id,
                           @PathVariable("accountID") Integer accountID,
                           @RequestParam("debt") int debt) throws Exception{
        return cardService.payDebt(id,accountID,debt);
    }
	
	
	
}
