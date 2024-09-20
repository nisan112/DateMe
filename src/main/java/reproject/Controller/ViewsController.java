package reproject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {
	//this is landing page..
		@GetMapping("/")
		public String getIndex() {
			System.out.println("i am first method...");
			return"index";
		}
		
		@GetMapping("/signin")
		public String getSignin() {
			return "redirect:/static/html/signin.html";
		}

}
