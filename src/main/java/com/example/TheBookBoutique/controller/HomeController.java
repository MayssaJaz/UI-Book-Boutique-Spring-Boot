package com.example.TheBookBoutique.controller;

import com.example.TheBookBoutique.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
	private static List<Book> books=
			List.of(new Book(1,"The fault in our stars","me",2,"Romance"),new Book(2,"The fault in our stars 2","meme",2,"Romance"));
	@GetMapping("/home")
		public String home(@RequestParam(required=false)String login, Model model)
	{System.out.println("loginnnn=é"+login);
		model.addAttribute("login",login);
		return "home";
	}


}
