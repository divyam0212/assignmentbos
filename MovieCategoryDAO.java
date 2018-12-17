package com.bookourshow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.bookourshow.util.ConnectionUtil;
import com.bookourshow.exception.BookOurShowException;
import com.bookourshow.model.Category;
import com.bookourshow.model.Language;
import com.bookourshow.model.Movie;
import com.bookourshow.model.MovieCategory;

public class MovieCategoryDAO {

	public MovieCategoryDAO() {

	}

	public MovieCategory fetchAllMovie(int movieId) throws BookOurShowException {
		String query = "select mc.movie_category_id,m.*,group_concat(c.category_id) as category,group_concat(l.language_name) as language "
						+"from movie_category mc "
						+"join movie m join category c join language l "
						+"on mc.fk_movie_id=m.movie_id and c.category_id=mc.fk_category_id "
						+"and l.language_id=mc.fk_language_id "
						+"where m.movie_id=? "
						+"group by m.movie_id";
		PreparedStatement pstmt = null;
		Connection connObj = null;
		ResultSet result = null;

		List<Category> categoryList=new ArrayList<>();
		List<Language> languageList=new ArrayList<>();
			
		MovieCategory movieCategory=new MovieCategory();
		try {
			connObj = ConnectionUtil.getConnection();
			pstmt = connObj.prepareStatement(query);
			pstmt.setInt(1, movieId);
			result = pstmt.executeQuery();
			while (result.next()) {
				
				int movieCategoryId=result.getInt("movie_category_id");
				
				String movieName=result.getString("movie_name");
				String movieReleaseDate=result.getString("release_date");
				
				Movie movie=new Movie(movieId, movieName, movieReleaseDate);
				
				String[] categoryStr=result.getString("category").split(",");
				String[] languageStr=result.getString("language").split(",");
				for(String catId:categoryStr) {
					Category category = new Category();
					int categoryId=Integer.parseInt(catId);
					category.setCategoryId(categoryId);
					
					CategoryDAO categoryDao=new CategoryDAO();
					String categoryName=categoryDao.fetchCategoryName(categoryId);
					category.setCategoryName(categoryName);
					categoryList.add(category);
				}
				for(String langId:languageStr) {
					Language language = new Language();
					int languageId=Integer.parseInt(langId);
					language.setLanguageId(languageId);
					
					LanguageDAO languageDAO=new LanguageDAO();
					String languageName=languageDAO.fetchLanguageName(languageId);
					language.setLanguageName(languageName);
					languageList=new ArrayList<>();
					languageList.add(language);
					
				}
				movieCategory = new MovieCategory(movieCategoryId,movie,categoryList,languageList);
			
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
				throw new BookOurShowException("Error while closing Object "+e);

			}
		}
		return movieCategory;
	}
	
}