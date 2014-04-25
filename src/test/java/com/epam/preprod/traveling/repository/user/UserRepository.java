package com.epam.preprod.traveling.repository.user;

import java.util.List;

import com.epam.preprod.traveling.domain.user.User;

public interface UserRepository {

	User findByName(String name);
	User findById(int id);
	List<User> findAll();
	boolean add(User user);
	
}
