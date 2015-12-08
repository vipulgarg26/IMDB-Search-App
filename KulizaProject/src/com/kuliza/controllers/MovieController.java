package com.kuliza.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kuliza.bean.Movie;
import com.kuliza.service.ImdbService;

@Controller
public class MovieController {
	Logger logger = Logger.getLogger(MovieController.class);

	@Autowired
	ImdbService iService;

	@RequestMapping("/")
	public String homePage() {
		BasicConfigurator.configure();
		return "home";
	}

	@RequestMapping(value = "/searchActor", headers = "Accept=application/json")
	public String resultPage(@RequestParam("actor") String actorName, ModelMap map) {
		BasicConfigurator.configure();
		List<Movie> movieObjectList = new ArrayList<>();
		String actorSearch = "http://www.myapifilms.com/imdb?name=" + actorName +"&format=JSON&filmography=0&limit=1&lang=en-us&exactFilter=0&bornDied=0&starSign=0&uniqueName=0&actorActress=0&actorTrivia=0&actorPhotos=N&actorVideos=N&salary=0&spouses=0&tradeMark=0&personalQuotes=0&starMeter=0";
		String movieSearch = null;
		JSONObject jsonObject = iService.getImdbData(actorSearch);
		String ImdbId = (String) jsonObject.get("idIMDB");
		//logger.info(ImdbId);
		String actorUrl = "http://www.imdb.com/name/" + ImdbId + "/";
		List<String> movieList = iService.getMovieList(actorUrl);
		for (String movie : movieList) {
			Movie movieObject = new Movie();
			movieSearch= "http://www.myapifilms.com/imdb?title="+ movie +"&format=JSON&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=N&exactFilter=0&limit=1&forceYear=0&lang=en-us&actors=N&biography=0&trailer=0&uniqueName=0&filmography=0&bornDied=0&starSign=0&actorActress=0&actorTrivia=0&movieTrivia=0&awards=0&moviePhotos=N&movieVideos=N&similarMovies=0&adultSearch=0";
			jsonObject= iService.getImdbData(movieSearch);	
			movieObject.setTitle((String)jsonObject.get("title"));
			movieObject.setUrlIMDB((String)jsonObject.get("urlIMDB"));
			movieObject.setUrlPoster((String)jsonObject.get("urlPoster"));
			movieObject.setRating((String)jsonObject.get("rating"));
			movieObject.setVotes((String)jsonObject.get("votes"));
			movieObject.setYear((String)jsonObject.get("year"));
			movieObject.setGlobalRelease((String)jsonObject.get("releaseDate"));
			List<String> countries = (List<String>) jsonObject.get("countries");
			List<String> runtime = (List<String>) jsonObject.get("runtime");
			List<String> genres = (List<String>) jsonObject.get("genres");
			movieObject.setCountry(countries.get(0));
			movieObject.setRuntime(runtime.get(0));
			movieObject.setRated((String) jsonObject.get("rated"));
			movieObject.setGenres(genres);
			movieObjectList.add(movieObject);
			}
		
		logger.info(movieObjectList.size());
		int index=0;
		//for(Movie movie : movieObjectList){
			for(index=0;index<movieObjectList.size();index++){
				Movie updatedMovie =iService.getReviews(movieObjectList.get(index));
				logger.info(index);
				movieObjectList.remove(index);
				movieObjectList.add(index, updatedMovie);
			}
			logger.info(movieObjectList.get(0));
			
		//}
		//map.addAttribute("data", movieObjectList);
		return "final";
	}

}
