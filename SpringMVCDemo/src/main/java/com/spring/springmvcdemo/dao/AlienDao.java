package com.spring.springmvcdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.spring.springmvcdemo.model.Alien;

@Component 
public class AlienDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Alien> getAliens(){
		Session session = sessionFactory.getCurrentSession();
		List<Alien> list = session.createQuery("from Alien",Alien.class).list();
		return list;
		
	}

}
