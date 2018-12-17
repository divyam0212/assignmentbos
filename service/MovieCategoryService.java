package com.bookourshow.service;

import org.springframework.stereotype.Service;

import com.bookourshow.dao.MovieCategoryDAO;
import com.bookourshow.exception.BookOurShowException;
import com.bookourshow.model.MovieCategory;

@Service
public class MovieCategoryService implements IMovieCategory {

	@Override
	public MovieCategory fetchAllMovie(int movieId) throws BookOurShowException{
		MovieCategory movieCategory=new MovieCategory();  
		MovieCategoryDAO movieCategoryDAO=new MovieCategoryDAO();
		movieCategory=movieCategoryDAO.fetchAllMovie(movieId);
		return movieCategory;
	}

}
