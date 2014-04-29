package com.epam.preprod.traveling.test.hsql.analitic;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;
import com.epam.preprod.traveling.test.hsql.DaoTestsTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class AnaliticRepositoryHsqlTest extends DaoTestsTemplate {

	@Autowired
    private AnaliticRepository analiticRepository;
	
	@Before
	public void setUp() throws Exception {
		jdbcTemplate.execute("DROP TABLE analitic");
	}
	@Ignore
	@Test
	public void test() {
		Analitic analitic = new Analitic("Mary", "Johnson", "mary.johnson@gmail.com", "jmary", null, "Analitic");
		
		analiticRepository.add(analitic);
		List<Analitic> analitics = analiticRepository.findAll();
		for (Analitic currentAnalitic : analitics) {
			System.out.println(currentAnalitic);
		}
	}

}
