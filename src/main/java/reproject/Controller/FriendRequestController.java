package reproject.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.DateMe.Models.FriendRequest;
import com.DateMe.Models.User;
import com.DateMe.Service.FriendRequestService;
import com.DateMe.Service.UserPersonalService;
@Controller
public class FriendRequestController {
private FriendRequestService friendrequestservice;
	
	
	public FriendRequestController(FriendRequestService friendrequestservice) {
		super();
		this.friendrequestservice = friendrequestservice;
	}
	
	@GetMapping("/getReqList")
	@ResponseBody
	public ResponseEntity<Map<String,User>> listOfRequestSender(@RequestParam("adminEmail") String adminEmail){
		System.out.println("i am called..");
		List<FriendRequest> listOfRequests = friendrequestservice.getFriendRequests(adminEmail);
		Map<String,User> result = friendrequestservice.getUserFromRequest(listOfRequests);
		System.out.println(result);
		return ResponseEntity.ok(result);
	}

}
