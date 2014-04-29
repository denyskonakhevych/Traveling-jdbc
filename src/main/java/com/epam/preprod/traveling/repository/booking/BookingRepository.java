package com.epam.preprod.traveling.repository.booking;

import java.util.List;

import com.epam.preprod.traveling.domain.booking.Booking;

public interface BookingRepository {

	Booking findByName(String name);
	Booking findById(int id);
	List<Booking> findAll();
	boolean add(Booking booking);
}
