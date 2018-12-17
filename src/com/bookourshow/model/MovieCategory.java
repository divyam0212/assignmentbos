package com.bookourshow.model;

public class MovieCategory {
	private int movieCategoryId;
	private Movie movie;
	private Category category;
	private Language language;
	public MovieCategory() {
		// TODO Auto-generated constructor stub
	}
	public MovieCategory(int movieCategoryId, Movie movie, Category category, Language language) {
		super();
		this.movieCategoryId = movieCategoryId;
		this.movie = movie;
		this.category = category;
		this.language = language;
	}
	public int getMovieCategoryId() {
		return movieCategoryId;
	}
	public void setMovieCategoryId(int movieCategoryId) {
		this.movieCategoryId = movieCategoryId;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	@Override
	public String toString() {
		return "MovieCategory \n[movieCategoryId=" + movieCategoryId + "\n, movie=" + movie + "\n, category=" + category
				+ "\n, language=" + language + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((movie == null) ? 0 : movie.hashCode());
		result = prime * result + movieCategoryId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieCategory other = (MovieCategory) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (movie == null) {
			if (other.movie != null)
				return false;
		} else if (!movie.equals(other.movie))
			return false;
		if (movieCategoryId != other.movieCategoryId)
			return false;
		return true;
	}

}
