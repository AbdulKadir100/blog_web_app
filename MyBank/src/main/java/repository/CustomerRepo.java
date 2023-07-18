package repository;

import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Integer>{
	  Page<Customer> findAll(Pageable pageable);
	  Optional<Customer> findById(int id);

}
