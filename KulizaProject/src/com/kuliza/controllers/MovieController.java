package com.kuliza.controllers;

import java.util.ArrayList;
import java.util.List;
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

// TODO: Auto-generated Javadoc
/**
 * The Class MovieController.- the controller class for the IMDB search app
 */
@Controller
public class MovieController {

	/** The logger. */
	Logger logger = Logger.getLogger(MovieController.class);

	/**
	 * instance of the IMDBService class to call the methods of the
	 * IMDBServiceImpl class.
	 */
	@Autowired
	ImdbService iService;

	/**
	 * Home page.
	 *
	 * @return the string
	 */
	@RequestMapping("/")
	public String homePage() {
		BasicConfigurator.configure();
		return "homePage";
	}

	/**
	 * Result page.
	 *
	 * @param actorName
	 *            the actor name
	 * @param map
	 *            the map
	 * @return the string
	 */
	@RequestMapping(value = "/searchActor", headers = "Accept=application/json")
	public String resultPage(@RequestParam("actor") String actorName, ModelMap map) {
		BasicConfigurator.configure();
		List<Movie> movieObjectList = new ArrayList<>();
		List<String> movieList = new ArrayList<>();
		int index;

		try {
			String actorSearch = "http://www.myapifilms.com/imdb?name=" + actorName
					+ "&format=JSON&filmography=0&limit=1&lang=en-us&exactFilter=0&bornDied=0&starSign=0&uniqueName=0&actorActress=0&actorTrivia=0&actorPhotos=N&actorVideos=N&salary=0&spouses=0&tradeMark=0&personalQuotes=0&starMeter=0";
			// logger.info(actorSearch);
			JSONObject jsonObject = iService.getImdbData(actorSearch); // getting
																		// imdb
																		// data
																		// for
																		// the
																		// actor
																		// from
																		// the
																		// api
																		// web
																		// service
			if (jsonObject != null) {
				String ImdbId = (String) jsonObject.get("idIMDB"); // stores
																	// actor's
																	// imdb Id
				// logger.info(ImdbId);
				String actorUrl = "http://www.imdb.com/name/" + ImdbId + "/"; // url
																				// of
																				// the
																				// actor's
																				// imdb
																				// home
																				// page
				movieList = iService.getMovieNames(actorUrl); // get list of top
																// 3 movies for
																// the actor
				movieObjectList = iService.getMovieDetails(movieList); // get
																		// the
																		// movies
																		// details
																		// for
																		// the 3
																		// movies

				// logger.info(movieObjectList.size());

				// updating the movies objects list with reviews data
				for (index = 0; index < movieObjectList.size(); index++) {
					Movie updatedMovie = iService.getReviewsFromImdb(movieObjectList.get(index)); // calling
																									// method
																									// to
																									// fetch
																									// the
																									// review
																									// details
																									// for
																									// each
																									// movie
					// logger.info(index);
					movieObjectList.remove(index); // removing the old movie
													// object
					movieObjectList.add(index, updatedMovie); // adding the new
																// movie object
																// with review
																// details
				}

				/*
				 * Movie updatedMovie =
				 * iService.getReviewsFromImdb(movieObjectList.get(0));
				 */
				/*
				 * logger.info(movieObjectList.get(0).getReviews().get(1));
				 */
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		map.addAttribute("actor", actorName); // throwing the actor's name and
												// his movie details on to the
												// next jsp page for display
		map.addAttribute("movieList", movieObjectList);
		return "searchResultPage";
	}

}
