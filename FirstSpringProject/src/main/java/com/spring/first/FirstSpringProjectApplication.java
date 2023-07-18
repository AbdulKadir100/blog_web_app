package com.spring.first;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

@SpringBootApplication
public class FirstSpringProjectApplication {

	public static void main(String[] args) {
		
	//BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
		
	ApplicationContext factory = new ClassPathXmlApplicationContext("spring.xml");
	Alien obj1 = (Alien) factory.getBean("alien"); //ID which is specified in xml 'alien' .
	obj1.code();
    System.out.println(obj1.getAge());
	
	
	}

}
