package com.spring.first;

import org.springframework.stereotype.Component;

@Component

public class Alien {
	private int age;
	private Laptop laptop;
	
	
	
//	public Alien() {
//		System.out.println("Alien Object Created");
//	}
//	
   public Alien(int age) {
		
		this.age = age;
	}

   public Laptop getLaptop() {
		return laptop;
	}


	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	
	public int getAge() {
		return age;
	}
	

	public void setAge(int age) {
		System.out.println("Value assigned");
		this.age = age;
	}
	
	public void code() {
		System.out.print("Im coding...");
	}

}
