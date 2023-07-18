package com.spring.springmvcactor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.springmvcactor.model.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
