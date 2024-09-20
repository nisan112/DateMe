package reproject.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.DateMe.Models.FriendRequest;
import com.DateMe.Models.User;
import com.DateMe.Service.UserPersonalService;

@Controller
public class UserController {
	private UserPersonalService userpersonalservice;
	
	
	public UserController(UserPersonalService userpersonalservice) {
		super();
		this.userpersonalservice = userpersonalservice;
	}




	@PostMapping("/signed")
	public String  userSignin(@ModelAttribute User newUser) {
		System.out.println("i am signed controller...");
		if(userpersonalservice.signinUserService(newUser)) {
			return "redirect:/static/html/login.html";
		}
		return"redirect:/static/html/signin.html";
	}

	
	
	@PostMapping("/login")
	public ModelAndView userLogin(@RequestParam("email") String email,@RequestParam("password") String password, ModelAndView mv) {
		Boolean stat = userpersonalservice.loginUserService(email,password);
		if(stat == true) {
			List<User> users = userpersonalservice.userListingService(email);
			mv.addObject("users", users);
			mv.addObject("adminEmail", email);
			mv.setViewName("home");
			return mv;
		}
		mv.setViewName("redirect:/static/html/login.html");
		return mv;	
	}
	@ResponseBody
	@PostMapping("/sendRequest")
	public ResponseEntity<String> sendRequest(@RequestParam("admin") String adminEmail,
		    @RequestParam("rec") String receiver,
		    @RequestParam("t") String time ) {
		DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime t = LocalDateTime.parse(time, df);
		userpersonalservice.requestSendingService(adminEmail,receiver,t);
		return ResponseEntity.ok("Request Sent");
		
		
		
	}
	

}

