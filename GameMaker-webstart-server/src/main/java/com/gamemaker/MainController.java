package com.gamemaker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller    // This means that this class is a Controller
//@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired // This means to get the bean called userRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;
	
	@PostMapping(path="/add") // Map ONLY GET Requests
	public @ResponseBody String addNewUser (@RequestParam String userName, @RequestParam String gameId, @RequestParam Integer score) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		ScoreDatabase n = new ScoreDatabase();
		n.setUserName(userName);
		n.setGameId(gameId);
		n.setScore(score);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping
	public String index(){
		return "redirect:/all";
	}
	
	@GetMapping(path="/all")
	public String getAllUsers(Model model) {
		// This returns a JSON or XML with the users

		model.addAttribute("scoreList",userRepository.groupByGameIdAndScore());
		//return userRepository.groupByGameIdAndScore();
		return "index.html";
	}
}
