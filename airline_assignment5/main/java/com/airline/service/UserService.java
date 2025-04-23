package com.airline.service;

import java.util.Optional;

import com.airline.dao.UserDao;
import com.airline.entity.User;
import com.airline.exception.ResourceAlreadyExistException;

public class UserService {
	public UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public boolean register(String name, String email, String password) {

		boolean status = false;

		Optional<User> foundUser = userDao.findAll().stream().filter(user -> user.getEmail().equalsIgnoreCase(email))
				.findFirst();

		if (foundUser.isPresent()) {
			throw new ResourceAlreadyExistException("User already exist with same email !!!");
		}

		boolean isSaved = userDao.save(new User(0, name, email, password));
		
		System.out.println(isSaved);

		if (isSaved) {
			System.out.println("user registered sucessfully!!");
			status = true;
		} else
			System.out.println("Failed to register user!!!");

		return status;
	}
	
	public User login(String email, String password) {
		return userDao.searchUserByEmailAndPassword(email, password);
	}
}
