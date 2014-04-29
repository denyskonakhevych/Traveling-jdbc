package com.epam.preprod.traveling.repository.analitic;

import java.util.List;

import com.epam.preprod.traveling.domain.analitic.Analitic;

public interface AnaliticRepository {

	Analitic findByName(String name);
	Analitic findById(int id);
	List<Analitic> findAll();
	boolean add(Analitic analitic);
}
