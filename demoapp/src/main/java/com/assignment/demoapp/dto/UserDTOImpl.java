package com.assignment.demoapp.dto;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.demoapp.entity.Address;
import com.assignment.demoapp.entity.User;

@Repository
public class UserDTOImpl implements UserDTO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void saveUser(User theUser) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(theUser);
	}

	@Override
	public User getUser(int userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		User theUser = currentSession.get(User.class, userId);
		int addressId = theUser.getAddress().getId();
		
		Address adr = currentSession.get(Address.class, addressId);
		theUser.setHomeAddress(adr.getHomeAddress());
		theUser.setWorkAddress(adr.getWorkAddress());		
		
		return theUser;
	}

	@Override
	public void deleteUser(int userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		User tempUser = 
				currentSession.get(User.class, userId);
		
		System.out.println("Found user: " + tempUser);

		// delete the instructors
		if(tempUser != null) {
			System.out.println("Deleting: " + tempUser);
			
			// Note: will ALSO delete associated "details" object
			// because of CascadeType.ALL
			currentSession.delete(tempUser);
		}
	}

	@Override
	public List<User> getUsers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User order by id", User.class);
		List<User> users = theQuery.getResultList();
		
		return users;
	}

}
