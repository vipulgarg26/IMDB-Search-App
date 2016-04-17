package com.imdb.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.imdb.bean.Movie;
import com.imdb.bean.Review;
import com.imdb.service.ImdbService;

// TODO: Auto-generated Javadoc
/**
 * The Class ImdbServiceImpl - service class to perform all the main data
 * preocessing tasks .
 */
@Service
@Transactional
public class ImdbServiceImpl implements ImdbService {

	/** The logger. */
	Logger logger = Logger.getLogger(ImdbServiceImpl.class);

	/*
	 * method to fetch imdb data from api web service using Rest template
	 * 
	 * @see com.kuliza.service.ImdbService#getImdbData(java.lang.String)
	 */
	@Override
	public JSONObject getImdbData(String searchUrl) {
		// TODO Auto-generated method stub

		BasicConfigurator.configure();
		JSONParser jsonParser = new JSONParser(); // creating a json parser to
													// parse the data string
													// into json object
		Object object;
		JSONObject jsonObject = null; // craeating a json object
		RestTemplate template = new RestTemplate(); // creating a rest template
													// to invoke the api web
													// service
		try {
			String searchData = template.getForObject(searchUrl, String.class); // fetching
																				// data
																				// from
																				// the
																				// web
																				// service
			
			logger.info("json string -----------------  " + searchData);
			
			if (searchData.charAt(0) == '{') {
				String filteredData = searchData.substring(1, searchData.length() - 1); // removing
																						// [
																						// ]
																						// from
																						// the
																						// input
																						// data
																						// string
																						// which
																						// is
																						// in
																						// the
																						// form
																						// [{
																						// }]
				// logger.info(filteredData);

				object = jsonParser.parse(filteredData); // parsing the data
															// string
															// into a json
															// object
				jsonObject = (JSONObject) object;
			} else {
				jsonObject = null;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonObject;
	}

	/*
	 * method to fetch names of actor's top 3 movies from IMDB page
	 * 
	 * @see com.kuliza.service.ImdbService#getMovieNames(java.lang.String)
	 */
	@Override
	public List<String> getMovieNames(String actorUrl) {
		// TODO Auto-generated method stub

		List<String> movies = new ArrayList<>(); // list to the names of the top
													// 3 movies
		Document document; // Jsoup document to get the web page for web
							// scrapping
		try {
			document = Jsoup.connect(actorUrl).get(); // store the web page
			Elements links = document.select("div[id=knownfor] > div > a"); // selecting
																			// the
																			// specific
																			// elements
																			// from
																			// web
																			// page

			for (Element link : links) {
				if (!link.text().equals("")) // removing the elements containing
												// no data
					movies.add(link.text()); // storing the movie names into the
												// list
			}
			movies.remove(movies.size() - 1); // reducing the size of movielist
												// to top 3 movies
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}

	/*
	 * method to get complete movie details from IMDB for each movie
	 * 
	 * @see com.kuliza.service.ImdbService#getMovieDetails(java.util.List)
	 */
	@Override
	public List<Movie> getMovieDetails(List<String> movieList) {
		// TODO Auto-generated method stub
		List<Movie> movieObjectList = new ArrayList<>();
		JSONObject jsonObject;
		String movieSearch;
		for (String movie : movieList) {
			Movie movieObject = new Movie();
			movieSearch = "http://www.myapifilms.com/imdb/idIMDB?title=" + movie
					+ "&token=5d40001a-7926-439f-82cb-19b811a6a8b7&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=2&exactFilter=0&limit=1&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&adultSearch=0&goofs=0&quotes=0&fullSize=0";
			jsonObject = getImdbData(movieSearch);
			movieObject.setTitle((String) jsonObject.get("title"));
			movieObject.setUrlIMDB((String) jsonObject.get("urlIMDB"));
			movieObject.setUrlPoster((String) jsonObject.get("urlPoster"));
			movieObject.setRating((String) jsonObject.get("rating"));
			movieObject.setVotes((String) jsonObject.get("votes"));
			movieObject.setYear((String) jsonObject.get("year"));
			movieObject.setGlobalRelease((String) jsonObject.get("releaseDate"));
			List<String> countries = (List<String>) jsonObject.get("countries");
			List<String> runtime = (List<String>) jsonObject.get("runtime");
			List<String> genres = (List<String>) jsonObject.get("genres");
			movieObject.setCountry(countries.get(0));
			movieObject.setRuntime(runtime.get(0));
			movieObject.setRated((String) jsonObject.get("rated"));
			movieObject.setGenres(genres);
			movieObjectList.add(movieObject);
		}
		return movieObjectList;
	}

	/*
	 * method to get top 3 reviews details for each movie
	 * 
	 * @see
	 * com.kuliza.service.ImdbService#getReviewsFromImdb(com.kuliza.bean.Movie)
	 */
	@Override
	public Movie getReviewsFromImdb(Movie movie) {
		// TODO Auto-generated method stub
		String otherRelease = null;
		int index = 0, pageCount = 0;

		List<String> reviewDataList = null; // list to store all
											// the review data
		List<String> reviewHeadList = null; // list to store all
											// the reveiew
											// heading
		List<String> reviewUsefulList = null; // list to store how
												// many people found
												// each review
												// useful
		List<Double> useMeterList = null; // list to store
											// usefulness percentage
											// for each review

		List<String> userPlaceList = null; // list to store each
											// reviewer location
		List<Integer> matchingIndexList = null; // list to store
												// list index
												// places of
												// matching
												// dates for
												// which review
												// does not have
												// a reviewer
												// location
		List<Review> reviews = new ArrayList<>(); // list to store review
													// objects
		List<Review> topReviews = new ArrayList<>(); // list to store the top 3
														// reviews
		Document documentReleaseDate, documentReview;

		try {
			documentReleaseDate = Jsoup.connect(movie.getUrlIMDB()).get();
			Elements link = documentReleaseDate.select("span.nobr > a[title=See all release dates]");
			otherRelease = link.text(); // Ito get the secondary Release date
			// logger.info(movie.getTitle());
			// logger.info(link.text());

			// do-while loop to search all the reviews pages to find the top 3
			// useful reviews

			do {
				reviewDataList = new ArrayList<>();
				reviewHeadList = new ArrayList<>();
				reviewUsefulList = new ArrayList<>();
				useMeterList = new ArrayList<>();
				userPlaceList = new ArrayList<>();
				matchingIndexList = new ArrayList<>();

				String reviewUrl = movie.getUrlIMDB() + "/reviews?start=" + pageCount; // stores
																						// the
																						// link
																						// of
																						// the
																						// imdb
																						// movie
																						// review
																						// pages

				// logger.info(reviewUrl);
				documentReview = Jsoup.connect(reviewUrl).userAgent("Chrome").get();
				Elements reviewUse = documentReview.select("div > small"); // list
																			// of
																			// how
																			// many
																			// people
																			// found
																			// reviews
																			// useful
				Elements reviewHeading = documentReview.select("h2"); // list of
																		// review
																		// headings
				Elements reviewBody = documentReview.select("div + p"); // list
																		// of
																		// review
																		// content
				Elements userPlace = documentReview.select("a + small"); // list
																			// of
																			// user
																			// locations
				Elements userName = documentReview.select("b + a"); // list of
																	// user
																	// names
				Elements postDateWithNoLocation = documentReview.select("a + br +small"); // list
																							// of
																							// post
																							// dates
																							// not
																							// having
																							// user
																							// location
				Elements allDates = documentReview.select(" br+ small"); // list
																			// of
																			// all
																			// the
																			// post
																			// dates

				reviewBody.remove(reviewBody.size() - 1);
				for (Element element : postDateWithNoLocation) {
					matchingIndexList.add(allDates.indexOf(element));

				}
				// logger.info(matchingIndexList);
				// logger.info(userName);
				// logger.info(reviewBody);
				// logger.info(reviewUse);

				// Storing document elements data into respective arraylists

				for (Element review : reviewBody) {
					reviewDataList.add(review.text());
				}
				for (Element review : reviewHeading) {
					reviewHeadList.add(review.text());
				}

				for (Element review : reviewUse) {
					reviewUsefulList.add(review.text());
				}

				for (Element review : userPlace) {
					userPlaceList.add(review.text());
				}

				// for calculating the usefulness percentage for each review
				for (index = 0; index < reviewUsefulList.size(); index++) {
					String[] splitter = reviewUsefulList.get(index).split("\\s+");
					// logger.info(Arrays.toString(splitter));
					if (splitter[0].matches("[0-9]+") && !(splitter[2].matches("[0-9]+"))) {
						int num1 = Integer.parseInt(splitter[0]);
						int num2 = Integer.parseInt(splitter[3]);
						double usePercent = (num1 * 100) / num2;
						// logger.info(usePercent);
						useMeterList.add(usePercent);
					}
				}

				// logger.info(useMeter.size());
				// logger.info(reviewHeadList.size());
				// logger.info(reviewDataList.size());
				// logger.info(reviewBody.get(0).text());
				// logger.info(reviewDataList.get(8));

				// adding empty spaces in the user location list for reviews
				// without a reviewer location
				for (int matchedIndex : matchingIndexList) {
					userPlaceList.add(matchedIndex, "");
				}

				// logger.info(reviewHeadList);

				// mapping all the review details into Review objects and
				// storing them in a reviewlist
				for (index = 0; index < useMeterList.size(); index++) {

					Review review = new Review();
					review.setData(reviewDataList.get(index));
					review.setHeading(reviewHeadList.get(index));
					review.setUsefulness(useMeterList.get(index));
					review.setUserInfo(userName.get(index).text().concat(" " + userPlaceList.get(index)));
					// logger.info(review.getUserInfo());
					reviews.add(review);

				}
				pageCount += 10;
			} while (reviewHeadList.size() > 0);

			Collections.sort(reviews); // Sorting the reviews based on their
										// usefulness

			logger.info(reviews.get(0).getUsefulness());
			logger.info(reviews.get(1).getUsefulness());
			logger.info(reviews.get(2).getUsefulness());

			if (reviews.size() >= 3) {
				for (index = 0; index < 3; index++) { // to save top 3 reviews
														// from the sorted
														// reviewList
					topReviews.add(reviews.get(index));
				}
			}
			else{
				
				for(index=0;index<reviews.size();index++){
					topReviews.add(reviews.get(index));
				}
			}

			// logger.info(topReviews);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movie.setOtherRelease(otherRelease);
		movie.setReviews(topReviews); // setting the top 3 review details into
										// the
										// movie object
		return movie;
	}

}
