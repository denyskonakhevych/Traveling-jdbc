package com.epam.preprod.traveling.test.hsql.booking;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.epam.preprod.traveling.domain.analitic.Analitic;
import com.epam.preprod.traveling.domain.booking.Booking;
import com.epam.preprod.traveling.domain.country.Country;
import com.epam.preprod.traveling.domain.hottel.Hottel;
import com.epam.preprod.traveling.domain.tour.Tour;
import com.epam.preprod.traveling.domain.user.User;
import com.epam.preprod.traveling.repository.analitic.AnaliticRepository;
import com.epam.preprod.traveling.repository.booking.BookingRepository;
import com.epam.preprod.traveling.repository.country.CountryRepository;
import com.epam.preprod.traveling.repository.hottel.HottelRepository;
import com.epam.preprod.traveling.repository.tour.TourRepository;
import com.epam.preprod.traveling.repository.user.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/persistenceContextTest.xml"})
public class BookingRepositoryHsqlTest {

	@Autowired
    private BookingRepository bookingRepository;
	@Autowired
    private TourRepository tourRepository;
	@Autowired
    private HottelRepository hottelRepository;
	@Autowired
    private CountryRepository countryRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
	private AnaliticRepository analiticRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		
		Country country = new Country();
		country.setName("Ukraine");
		country.setDescription("description");
		country.setClimate("climate");
		countryRepository.add(country);
		
		Hottel hottel = new Hottel();
		hottel.setCountry(countryRepository.findAll().get(0));
		hottel.setDescription("description");
		hottel.setName("hottel");
		hottel.setStars(5);
		hottelRepository.add(hottel);
		
		Tour tour = new Tour();
		tour.setHottel(hottel);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		tour.setFrom(calendar.getTime());
		
		calendar.set(Calendar.YEAR, 2014);
		calendar.set(Calendar.MONTH, 7);
		calendar.set(Calendar.DAY_OF_MONTH, 13);
		tour.setTo(calendar.getTime());
		
		tour.setHottel(hottelRepository.findAll().get(0));
		tour.setNumberOfPeole(2);
		tour.setPrice(7999.99f);
		tourRepository.add(tour);
		
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
		
		Analitic analitic = new Analitic();
		analitic.setFirstName("first_name");
		analitic.setSecondName("second_name");
		analitic.setEmail("email");
		analiticRepository.add(analitic);
		
		Booking booking = new Booking();
		booking.setTour(tourRepository.findAll().get(0));
		booking.setTotalPrice(7999.99f);
		booking.setStatus("Ordered");
		booking.setOrderedBy(userRepository.findAll().get(0));
		booking.setManagedBy(analiticRepository.findAll().get(0));
		
		bookingRepository.add(booking);
		List<Booking> bookings = bookingRepository.findAll();
		for (Booking currentBooking : bookings) {
			System.out.println(currentBooking);
		}
	}

}
