package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Account;
import entity.Customer;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
	Account deleteAccountById(int id);
	Optional<Account> findById(int id);
	Optional<Account> findByCustomer(Customer customer);
	Optional<Account> getByIban(String iban);
	

}
