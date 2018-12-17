package com.bookourshow.service;

import com.bookourshow.exception.BookOurShowException;
import com.bookourshow.model.MovieCategory;

public interface IMovieCategory {

	public MovieCategory fetchAllMovie(int movieId) throws BookOurShowException;
	
}
