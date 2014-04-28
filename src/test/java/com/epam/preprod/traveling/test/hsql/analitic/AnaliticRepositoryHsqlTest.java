package com.epam.preprod.traveling.test.hsql.analitic;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class AnaliticRepositoryHsqlTest {

	@Autowired
    private AnaliticRepository analiticRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		Analitic analitic = new Analitic();
		analitic.setFirstName("first_name");
		analitic.setSecondName("second_name");
		analitic.setEmail("email");
		
		analiticRepository.add(analitic);
		List<Analitic> analitics = analiticRepository.findAll();
		for (Analitic currentAnalitic : analitics) {
			System.out.println(currentAnalitic);
		}
	}

}
