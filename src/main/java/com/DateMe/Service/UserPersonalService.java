package com.DateMe.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.DateMe.DAO.userDAO;
import com.DateMe.Models.FriendRequest;
import com.DateMe.Models.User;

@Service
public class UserPersonalService {
	private userDAO userdao;
	
	public UserPersonalService(userDAO userdao) {
		this.userdao = userdao;
	}
	
	
	
	//this service class allows User's signin,login,logout,delete operations..
	@Transactional
	public Boolean signinUserService(User newUser) {
		if(userExist(newUser.getEmail(),newUser.getPhone())){
			System.out.println("User already exist controller");
			return false;	
		}
		Boolean stat = userdao.addUser(newUser);
		if(stat) {
			System.out.println("User added successfully from Service");
			return true;
		}
		System.out.println("User Cannot be added(Service)");
		return false;
		
	}
	@Transactional
	public Boolean loginUserService(String email,String password) {
			User user = userdao.getUserByEmail(email);
			if(user != null) {
				String outputEmail = user.getEmail();
				System.out.println("useremail :"+ outputEmail);
				if( outputEmail.equals(email) && user.getPassword().equals(password)){
					System.out.println("Logged in ");
					return true;
				}
				System.out.println("Not logged in, Email or password wrong");
				return false;
			}
			System.out.println("User doesnot exist");
			return false;
			
			
		}
	@Transactional
	public Boolean userExist(String email,String phone) {
		User userByEmail = userdao.getUserByEmail(email);
		User userByPhone = userdao.getUserByPhone(phone);
		 if (userByEmail != null || userByPhone != null) {
		        return true; // A user exists with either the provided email or phone
		    } else {
		        return false; // No user exists with either the provided email or phone
		    }
	}
	@Transactional
	public List<User> userListingService(String adminEmail){
		List<User> allUsers = userdao.getListOfAllUser(adminEmail);
		return allUsers;
	}
	
	@Transactional
	public Boolean requestSendingService(String sender,String receiver,LocalDateTime time) {
		User reqsender = userdao.getUserByEmail(sender);
		User reqreceiver = userdao.getUserByEmail(receiver);
		FriendRequest fr = new FriendRequest(reqsender,reqreceiver,time);
		if(userdao.reqSender(fr)) {
			System.out.println("req send successflly from service");
			return true;
		}
		System.out.println("error sending req, service");
		return false;
	}
	}


