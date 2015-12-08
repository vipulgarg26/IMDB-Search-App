package com.kuliza.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.kuliza.bean.Movie;

public interface ImdbService{
	
	public JSONObject getImdbData(String data);
	public List<String> getMovieList(String actorUrl);
	public Movie getReviews(Movie movie);

}
