package com.bookourshow.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.bookourshow.exception.BookOurShowException;
import com.bookourshow.service.MovieService;

@ComponentScan({"com.bookourshow"})
@SessionAttributes("user")
@Controller
public class TestController {
	
	private MovieService movieService;
	@Autowired
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/all")
	public ModelAndView all() {

		String path="success";
		ModelAndView modelAndView=new ModelAndView();
		try {
			modelAndView.setViewName(path);
			modelAndView.addObject("movieList",movieService.fetchAllMovie());
		} catch (BookOurShowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
}
