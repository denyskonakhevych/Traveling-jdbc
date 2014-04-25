package com.epam.preprod.traveling.repository.tour;

import java.util.List;

import com.epam.preprod.traveling.domain.tour.Tour;

public interface TourRepository {

	Tour findByName(String name);
	Tour findById(int id);
	List<Tour> findAll();
	boolean add(Tour tour);
}
