package com.codejava1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class AppController {
	
	    @Autowired
	    private UserRepository repo;
	
	    @GetMapping("")
	    public String viewHomePage() {
	        return "index";
	    }
	    
	    @GetMapping("/register")
	    public String showSignUpForm(Model model)
	    {
	    	model.addAttribute("user",new User());
	    	
	    	return "signup_form";
	    }
	    
//	    @GetMapping("/login2")
//	    public String signinForm(Model model)
//	    {
//	    	List<User> listusers = repo.findAll();
//	    	return "login";
//	    }
	    
	    
	    @PostMapping("/process_register")
	    
	    public String processRegisteration(User user) {
	    	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	    	String encodedPassword = encoder.encode(user.getPassword());
	    	user.setPassword(encodedPassword);
	    	
	        
	        repo.save(user);
	         
	        return "register_success";
	    }
	    
	   
	    @GetMapping("/users")
	    public String viewUsersList(Model model)
	    {
	    	List<User> listusers = repo.findAll();
	    	model.addAttribute("listUsers", listusers);
	    	model.addAttribute("Post",new Post());
	    	
	    	return "users";
	    }
  
	    
	    @PostMapping("/post")

		public String postSend(Post post) {
			//System.out.println(Post.getPost());
			//Post.setPost(Post.getPost());
			//Post.setPost("hello akash");
			repo.save(post);
			System.out.println("hii");
			return "post_send";
		}
}
