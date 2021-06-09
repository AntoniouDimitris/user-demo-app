package com.assignment.demoapp.service;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.demoapp.dto.UserDTO;
import com.assignment.demoapp.entity.Address;
import com.assignment.demoapp.entity.Authorities;
import com.assignment.demoapp.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDTO userDTO;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveUser(User theUser) {
		
		
		Authorities authority = new Authorities("ROLE_USER");
		authority.setUser(theUser);
		theUser.setAuthorities(Arrays.asList(authority));
		
		Session currentSession = sessionFactory.getCurrentSession();
		Address address = new Address();
		address.setWorkAddress(theUser.getWorkAddress());
		address.setHomeAddress(theUser.getHomeAddress());
		address.setUser(theUser);
		
		currentSession.save(address);
		
		theUser.setAddress(address);
		userDTO.saveUser(theUser);
		
		
	}

	@Override
	@Transactional
	public User getUser(int userId) {
		
		User tempUser = userDTO.getUser(userId);
		
		return tempUser;
	}

	@Override
	@Transactional
	public void deleteUser(int userId) {
		
		userDTO.deleteUser(userId);
	}

	@Override
	@Transactional
	public List<User> getUsers() {
		
		List<User> tempUserList = userDTO.getUsers();
		return tempUserList;
	}

}
