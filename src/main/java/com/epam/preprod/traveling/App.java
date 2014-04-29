package com.epam.preprod.traveling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.user.UserRepository;

public class App {

	public static void main(String[] args) {
		
		ApplicationContext appCtx = new ClassPathXmlApplicationContext("persistenceContext.xml");
        UserRepository users = appCtx.getBean("userDao", UserRepository.class);
        User mary = new User();
        mary.setFirstName("Mary");
        mary.setSecondName("Johnson");
        mary.setSex("female");
        mary.setAddress("Mary's address");
        mary.setEmail("mary.johnson@gmail.com");
        mary.setPassword("jmary");
        List<String> maryTelephones = new ArrayList<>();
        maryTelephones.add("+38(012)1234567");
        mary.setTelephones(maryTelephones);
        users.add(mary);
        
        User mike = new User();
        mike.setFirstName("Mike");
        mike.setSecondName("Tison");
        mike.setSex("male");
        mike.setAddress("Mike's address");
        mike.setEmail("mike.tison@gmail.com");
        mike.setPassword("tmike");
        List<String> mikeTelephones = new ArrayList<>();
        mikeTelephones.add("+38(012)7654321");
        mike.setTelephones(mikeTelephones);
        users.add(mike);
        
        List<User> userList = users.findAll();
        for (User user : userList) {
            System.out.println(user);
        }
        ((ClassPathXmlApplicationContext)appCtx).close(); // TODO: Is it correct
	}
}
