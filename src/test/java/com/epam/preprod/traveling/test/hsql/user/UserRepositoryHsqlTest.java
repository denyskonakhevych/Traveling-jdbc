package com.epam.preprod.traveling.test.hsql.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.user.UserRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class UserRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
		//jdbcTemplate.execute("DROP SCHEMA PUBLIC CASCADE");
		jdbcTemplate.execute("DROP TABLE user");
	}
	@Ignore
	@Test
	public void test() {
		List<String> usersTelephones = new ArrayList<>();
		usersTelephones.add("+38(012)1234567");
		User user = new User("Mary", "Johnson", "mary.johnson@gmail.com", "jmary", null, "female", usersTelephones, "Mary's address");
		
		userRepository.add(user);
		List<User> users = userRepository.findAll();
		for (User currentUser : users) {
			System.out.println(currentUser);
		}
	}

}
