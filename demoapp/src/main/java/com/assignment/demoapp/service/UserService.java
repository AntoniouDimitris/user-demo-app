package com.assignment.demoapp.service;

import java.util.List;

import com.assignment.demoapp.entity.User;

public interface UserService {

	public void saveUser(User theUser);
	
	public User getUser(int userId);
	
	public void deleteUser(int userId);
	
	public List<User> getUsers();
}
