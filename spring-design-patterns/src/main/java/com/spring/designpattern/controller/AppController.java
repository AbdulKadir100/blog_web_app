package com.spring.designpattern.controller;


import com.spring.designpattern.factory.Pet;
import com.spring.designpattern.factory.PetFactory;

import builder.Contact;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AppController {
	
	@Autowired
	private PetFactory petFactory;

    @GetMapping
    public String getDefault(){
        return "{\"message\": \"Hello World\"}";
    }

    @PostMapping("adoptPet/{type}/{name}")
    public Pet adoptPet(@PathVariable String type,@PathVariable String name) {
    	Pet pet= this.petFactory.createPet(type);
    	pet.setName(name);
    	pet.feed();
    	return pet;
    }
    
    @GetMapping("presidents")
    public List<Contact> getPresidents(){
    	List<Contact> contacts = new ArrayList<>();
    	Contact c = new Contact();
    	c.setFirstName("Abdul");
    	c.setLastName("Kadir");
    	contacts.add(c);
    	
    	contacts.add(new Contact("Jhon","Adams",null));
    	return contacts;
    }
    

}
