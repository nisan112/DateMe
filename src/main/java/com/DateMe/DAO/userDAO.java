package com.DateMe.DAO;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.DateMe.Models.FriendRequest;
import com.DateMe.Models.User;




@Repository
public class userDAO {
	private SessionFactory sessionfactory;
	
	@Autowired
	public userDAO(SessionFactory sessionfactory){
		this.sessionfactory = sessionfactory;
		
	}
	
	public Boolean addUser(User newuser) {
		try {
		Session session = sessionfactory.getCurrentSession();
		session.persist(newuser);
		session.flush();
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}	
	}
	
	public User getUserByEmail(String email) {
		 try {
		        Session session = sessionfactory.getCurrentSession();
		        String hql = "FROM User WHERE email = :email";
		        List<User> users = session.createQuery(hql, User.class)
		                                  .setParameter("email", email)
		                                  .getResultList();
		        
		        if (users.size() >= 1) {
		            return users.get(0); // Single user found
		        } else {
		            return null; // No user found or multiple users found
		        }
		    } catch (Exception e) {
		        e.printStackTrace(); // Log the exception properly
		        return null; // Return null in case of an exception
		    }
}
	
	
	public User getUserByPhone(String phone) {
		Session session = sessionfactory.getCurrentSession();
		String hql = "From User where phone = :phone";
		List<User> userByPhone = session.createQuery(hql, User.class).setParameter("phone", phone).getResultList();
		if(userByPhone.size() == 1) {
			return userByPhone.get(0);
		}
		
		return null;
	}
	
	
	public List<User> getListOfAllUser(String adminEmail){
		Session session = sessionfactory.getCurrentSession();
		String hql = "From User where email != :adminEmail";
		List<User> allUsers = session.createQuery(hql,User.class).setParameter("adminEmail", adminEmail).getResultList();
		return allUsers;
	}
	public Boolean reqSender(FriendRequest friendrequest) {
		try {
			Session session = sessionfactory.getCurrentSession();
			session.persist(friendrequest);
			session.flush();
			return true;
			}
			catch(Exception e) {
				e.printStackTrace();
				return false;
			}	
		}
	
	public List<FriendRequest> getFriendRequest(String adminEmail){
		Session session = sessionfactory.getCurrentSession();
		User user  = getUserByEmail(adminEmail);
		return user.getReceivedrequest();

		
	}
	
	//this function should return map with "request time"as key and user object as value....
	public Map<String, User> getUserFromRequest(List<FriendRequest> sender){
		
		Map<String, User> resultedMap = new HashMap<>();
		for(FriendRequest  fr: sender) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String time = fr.getTime().format(formatter);
			User reqsender = fr.getSender();
			resultedMap.put(time,reqsender);		
		}
		return resultedMap;
		
		
	}

}

