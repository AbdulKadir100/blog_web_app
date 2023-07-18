package com.spring.springmvcactor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.springmvcactor.model.Actor;
import com.spring.springmvcactor.repository.ActorRepository;

@Service
public class ActorService implements ActorServiceInterface{
	
	@Autowired
	private final ActorRepository actorRepository;
	
	public ActorService(ActorRepository repo) {
		actorRepository = repo;
	}

	@Override
	public List<Actor> getAllActorList(){
		Iterable<Actor> actors = this.actorRepository.findAll();
		List<Actor> actorss = new ArrayList<>();
		actors.forEach(actor ->{
			Actor act = new Actor();
			act.setActor_id(actor.getActor_id());
			act.setFirst_name(actor.getFirst_name());
			act.setLast_name(actor.getLast_name());
			act.setLast_update(actor.getLast_update());
			actorss.add(act);
		});
		return actorss;
	}
	
	
	@Override
	public Actor saveActor(Actor actor) {
		return actorRepository.save(actor);
	}

	
	@Override
	public List<Actor> getAllActor() {
	   return actorRepository.findAll();
	}

	@Override
	public Actor get(Integer id) {
		return actorRepository.findById(id).get();
	}

	@Override
	public void delete(Integer id) {
		actorRepository.deleteById(id);
		
	}

}
