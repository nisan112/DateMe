package com.DateMe.Models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class FriendRequest {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	@ManyToOne
	@JoinColumn(name = "receiver_id")
	private User receiver;
	private LocalDateTime time;
	public FriendRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FriendRequest(User sender, User receiver, LocalDateTime time) {
		super();
		
		this.sender = sender;
		this.receiver = receiver;
		this.time = time;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReceiver() {
		return receiver;
	}
	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "FriendRequest [sender=" + sender + ", receiver=" + receiver + ", time=" + time + "]";
	}
	
	

}
