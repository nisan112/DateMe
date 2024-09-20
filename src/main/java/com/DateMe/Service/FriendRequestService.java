package com.DateMe.Service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.DateMe.DAO.userDAO;
import com.DateMe.Models.FriendRequest;
import com.DateMe.Models.User;

@Service
public class FriendRequestService {
private userDAO userdao;
	
	public FriendRequestService(userDAO userdao) {
		this.userdao = userdao;
	}
	
	
	@Transactional
	public List<FriendRequest> getFriendRequests(String adminEmail){
		return userdao.getFriendRequest(adminEmail);
		
	}
	@Transactional
	public Map<String,User> getUserFromRequest(List<FriendRequest> sender){
		return userdao.getUserFromRequest(sender);
	}

}
