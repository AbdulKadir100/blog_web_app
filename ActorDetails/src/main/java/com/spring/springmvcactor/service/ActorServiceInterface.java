package com.spring.springmvcactor.service;

import java.util.List;

import com.spring.springmvcactor.model.Actor;

public interface ActorServiceInterface {
	//method to add more actor
	public Actor saveActor(Actor actor);
	
	//to fetch all the actor
	public List<Actor> getAllActor();
	public List<Actor> getAllActorList();
	
	//to get single actor
	
	public Actor get(Integer id);
	
	//deleting
	public void delete(Integer id);

}
