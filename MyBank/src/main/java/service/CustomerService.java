package service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import entity.Customer;
import repository.CustomerRepo;

@Service
public class CustomerService {
	 private final CustomerRepo customerRepo;

	    public CustomerService(CustomerRepo customerRepo) {
	        this.customerRepo = customerRepo;
	    }

	    public Customer create(Customer customer){
	        return customerRepo.save(customer);
	    }

	    public Page<Customer> list(PageRequest pageRequest){
	        return customerRepo.findAll(pageRequest);
	    }

	    public Optional<Customer> get(int id){
	        return customerRepo.findById(id);
	    }
	    /*
	     * The @Modifying annotation is used to enhance the @Query annotation so that 
	     * we can execute not only SELECT queries, but also INSERT, UPDATE, DELETE, 
	     * and even DDL queries.
	     */
	    
	    @Modifying
	    public Customer update(Customer customer){
	        return customerRepo.save(customer);
	    }
	    
	    public void delete(int id) {
             Customer customer =customerRepo.findById(id)
	                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Account not found with id"+id));
             
             boolean currencyControl = 
            		 customer.getAccounts().stream().distinct().anyMatch(account->account.getBalance()!=0);
             
             /*boolean cardControl=
             customer.getCards().stream().distinct().anyMatch(creditCard -> creditCard.getDebt() !=0);*/
             
             if(currencyControl) {

                 throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"the customer's account has money.");
             }
             customerRepo.delete(customer);
	    }

}
