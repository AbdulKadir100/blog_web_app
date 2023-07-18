package com.spring.springmvcactor.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.springmvcactor.model.Actor;
import com.spring.springmvcactor.service.ActorServiceInterface;

@RestController
@RequestMapping("/actor")
@CrossOrigin
public class ActorController {
	
	@Autowired
	private final ActorServiceInterface actService;
	
	public ActorController(ActorServiceInterface actService) {
		this.actService = actService;
	}
	
	@PostMapping("/add")
	public String add(@RequestBody Actor actor) {
		actService.saveActor(actor);
		return "Actor added successfully!..";
	}
	
	@GetMapping("/getAll")
	public List<Actor> getAllactor(){
		return actService.getAllActor();
	}
	
	@GetMapping("/getAll2")
	public String getAllactor2(Model model){
		List<Actor> actors = this.actService.getAllActorList();
		model.addAttribute("actors",actors);
		return "index";
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Actor> getSingle(@PathVariable Integer id){
		try {
			Actor a = actService.get(id);
			return new ResponseEntity<Actor>(a,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Actor> update(@RequestBody Actor actor,@PathVariable Integer id){
		try {
		     Actor s = actService.get(id);
			actService.saveActor(actor);
			return new ResponseEntity<Actor>(s,HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Actor>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		actService.delete(id);
		return "Deleted Actor with id "+id;
	}
}
