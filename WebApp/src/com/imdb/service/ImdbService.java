package com.imdb.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.imdb.bean.Movie;

// TODO: Auto-generated Javadoc
/**
 * The Interface ImdbService.
 */
public interface ImdbService{
	
	/**
	 * Gets the imdb data.
	 *
	 * @param data the data
	 * @return the imdb data
	 */
	public JSONObject getImdbData(String data);
	
	/**
	 * Gets the movie names.
	 *
	 * @param actorUrl the actor url
	 * @return the movie names
	 */
	public List<String> getMovieNames(String actorUrl);
	
	/**
	 * Gets the reviews from imdb.
	 *
	 * @param movie the movie
	 * @return the reviews from imdb
	 */
	public Movie getReviewsFromImdb(Movie movie);
	
	/**
	 * Gets the movie details.
	 *
	 * @param movieList the movie list
	 * @return the movie details
	 */
	public List<Movie> getMovieDetails(List<String> movieList);

}
