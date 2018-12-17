package com.bookourshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookourshow.util.ConnectionUtil;
import com.bookourshow.model.Category;
import com.bookourshow.model.Language;
import com.bookourshow.model.Movie;
import com.bookourshow.model.MovieCategory;

public class MovieCategoryDAO {

	public MovieCategoryDAO() {

	}

	public List<MovieCategory> fetchAllMovie() {
		String query = "select * from movie_category";
		PreparedStatement pstmt = null;
		Connection connObj = null;
		ResultSet result = null;
	
		List<MovieCategory> movielist = new ArrayList<MovieCategory>();
		
		try {
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			result = pstmt.executeQuery();
			while (result.next()) {
				int movieCategoryId = result.getInt("movie_category_id");
				int movieId=result.getInt("fk_movie_id");
				int categoryId=result.getInt("fk_category_id");
				int languageId=result.getInt("fk_language_id");
				
				Movie movie = new Movie();
				movie.setMovieId(movieId);
				
				Category category = new Category();
				category.setCategoryId(categoryId);
				
				Language language = new Language();
				language.setLanguageId(languageId);
				
				MovieCategory movieCategory = new MovieCategory(movieCategoryId,movie,category,language);
				movielist.add(movieCategory);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (result != null) {
					result.close();
				}

				if (pstmt != null) {
					pstmt.close();
				}

				if (connObj != null) {
					connObj.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				// throw new PayrollException("Error while closing Object "+e);

			}
		}
		return (movielist);
	}
}