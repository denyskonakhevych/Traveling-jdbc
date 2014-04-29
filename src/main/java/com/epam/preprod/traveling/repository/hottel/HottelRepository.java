package com.epam.preprod.traveling.repository.hottel;

import java.util.List;

import com.epam.preprod.traveling.domain.hottel.Hottel;

public interface HottelRepository {

	Hottel findByName(String name);
	Hottel findById(int id);
	List<Hottel> findAll();
	boolean add(Hottel hottel);
}
