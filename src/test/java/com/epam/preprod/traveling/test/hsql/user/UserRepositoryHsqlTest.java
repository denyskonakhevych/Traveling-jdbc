package com.epam.preprod.traveling.test.hsql.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class UserRepositoryHsqlTest {

	@Autowired
    private UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		User user = new User();
		user.setFirstName("firstName");
		user.setSecondName("secondName");
		user.setSex("male");
		user.setEmail("email");
		user.setPassword("***");
		List<String> telephones = new ArrayList<String>();
		telephones.add("telephone1");
		user.setTelephones(telephones);
		user.setAddress("Address");
		
		userRepository.add(user);
		List<User> users = userRepository.findAll();
		for (User user1 : users) {
			System.out.println(user1);
		}
	}

}
