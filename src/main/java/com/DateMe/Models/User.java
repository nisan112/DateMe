package com.DateMe.Models;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	private String firstname;
	private String lastname;
	private int age;
	private String phone;
	@Id
	private String email;
	private String password;
	private String repass;
	@OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
	private List<FriendRequest> sentrequest;
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private List<FriendRequest> receivedrequest;
	@ManyToMany
	@JoinTable(
			name = "user_friend",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name="friend_id")
			)
	
	
	private Set<User> friend;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public User(String firstname, String lastname, int age, String phone, String email, String password,
			String repass) {
		super();
		
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.repass = repass;
	}
	
	

	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getRepass() {
		return repass;
	}



	public void setRepass(String repass) {
		this.repass = repass;
	}
	
	
	public List<FriendRequest> getSentrequest() {
		return sentrequest;
	}



	public void setSentrequest(List<FriendRequest> sentrequest) {
		this.sentrequest = sentrequest;
	}



	public List<FriendRequest> getReceivedrequest() {
		return receivedrequest;
	}



	public void setReceivedrequest(List<FriendRequest> receivedrequest) {
		this.receivedrequest = receivedrequest;
	}



	public Set<User> getFriend() {
		return friend;
	}



	public void setFriend(Set<User> friend) {
		this.friend = friend;
	}



	@Override
	public String toString() {
		return "User [ firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + ", phone="
				+ phone + ", email=" + email + ", password=" + password + ", repass=" + repass + "]";
	}
	
	

}
