package entity;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.util.Streamable;

import Dto.CustomerDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "customer")
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer")
    private Set<Card> cards;

    public CustomerDto customerDto(){
        return CustomerDto.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                .accounts(accounts.stream()
                        .map(Account::accountDto)
                        .collect(Collectors.toSet()))
                .cards(cards.stream()
                        .map(Card::cardDto)
                        .collect(Collectors.toSet()))
                .build();
    }

	public Set<Account> getAccounts() {
		return accounts;
		// TODO Auto-generated method stub
		return null;
	}
}
