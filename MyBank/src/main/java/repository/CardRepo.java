package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import entity.Card;

@Repository
public interface CardRepo extends JpaRepository<Card, Integer>{
	Optional<Card> findById(int id);
    Optional<Card> findByDebtOrderById(int id);

}
