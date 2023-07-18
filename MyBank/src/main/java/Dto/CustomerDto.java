package Dto;

import java.time.LocalDate;
import java.util.Set;

import entity.Customer;
import enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDto {
    private int id;
    @NotBlank(message = "Name is necessary")
    private String name;
    @NotBlank(message = "Surname is necessary")
    private String surname;
    private Set<AccountDto> accounts;
    private Set<CardDto> cards;

    public Customer toCustomer(){
        return Customer.builder()
                .id(this.id)
                .name(this.name)
                .surname(this.surname)
                /*.accounts(accounts.stream()
                    .map(AccountDto::toAccount)
                    .collect(Collectors.toSet()))
                .cards(cards.stream()
                    .map(CardDto::toCard)
                    .collect(Collectors.toSet()))*/
                .build();
    }
}
