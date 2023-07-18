package Dto;

import entity.Card;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDto {
	    private int id;
	    private String cardNumber;
	    private int boundary;
	    private int debt;
	    
	    
	    public Card toCard() {
	    	return Card.builder()
	                .id(this.id)
	                .cardNumber(this.cardNumber)
	                .boundary(this.boundary)
	                .debt(this.debt)
	                .build();
	    }


		public int getBoundary() {
			return boundary;
		}


		public int getDebt() {
			return debt;
		}


		public void setDebt(int debt) {
			this.debt=debt;
			
		}


}
