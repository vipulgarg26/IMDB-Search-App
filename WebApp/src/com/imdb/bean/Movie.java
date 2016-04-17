package com.imdb.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * The Class Movie- Bean class to store details of the movie
 */
@Component
public class Movie {

	/** to store the movie details. */
	String title,urlIMDB,urlPoster,votes,rating,year,runtime,rated,globalRelease,country,otherRelease;
	
	/** to store the list of genres of the movie. */
	List<String> genres;
	
	/** to store the reviews of the movie. */
	@Autowired
	List<Review> reviews;

	
	/**
	 * Gets the reviews.
	 *
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * Sets the reviews.
	 *
	 * @param reviews the new reviews
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	/**
	 * Gets the other release.
	 *
	 * @return the other release
	 */
	public String getOtherRelease() {
		return otherRelease;
	}

	/**
	 * Sets the other release.
	 *
	 * @param otherRelease the new other release
	 */
	public void setOtherRelease(String otherRelease) {
		this.otherRelease = otherRelease;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the global release.
	 *
	 * @return the global release
	 */
	public String getGlobalRelease() {
		return globalRelease;
	}

	/**
	 * Sets the global release.
	 *
	 * @param globalRelease the new global release
	 */
	public void setGlobalRelease(String globalRelease) {
		this.globalRelease = globalRelease;
	}

	/**
	 * Gets the rated.
	 *
	 * @return the rated
	 */
	public String getRated() {
		return rated;
	}

	/**
	 * Sets the rated.
	 *
	 * @param rated the new rated
	 */
	public void setRated(String rated) {
		this.rated = rated;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the url imdb.
	 *
	 * @return the url imdb
	 */
	public String getUrlIMDB() {
		return urlIMDB;
	}

	/**
	 * Sets the url imdb.
	 *
	 * @param urlIMDB the new url imdb
	 */
	public void setUrlIMDB(String urlIMDB) {
		this.urlIMDB = urlIMDB;
	}

	/**
	 * Gets the url poster.
	 *
	 * @return the url poster
	 */
	public String getUrlPoster() {
		return urlPoster;
	}

	/**
	 * Sets the url poster.
	 *
	 * @param urlPoster the new url poster
	 */
	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}

	/**
	 * Gets the genres.
	 *
	 * @return the genres
	 */
	public List<String> getGenres() {
		return genres;
	}

	/**
	 * Sets the genres.
	 *
	 * @param genres the new genres
	 */
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	/**
	 * Gets the votes.
	 *
	 * @return the votes
	 */
	public String getVotes() {
		return votes;
	}

	/**
	 * Sets the votes.
	 *
	 * @param votes the new votes
	 */
	public void setVotes(String votes) {
		this.votes = votes;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * Gets the year.
	 *
	 * @return the year
	 */
	public String getYear() {
		return year;
	}

	/**
	 * Sets the year.
	 *
	 * @param year the new year
	 */
	public void setYear(String year) {
		this.year = year;
	}

	/**
	 * Gets the runtime.
	 *
	 * @return the runtime
	 */
	public String getRuntime() {
		return runtime;
	}

	/**
	 * Sets the runtime.
	 *
	 * @param runtime the new runtime
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Movie [title=" + title + ", urlIMDB=" + urlIMDB + ", urlPoster=" + urlPoster + ", votes=" + votes
				+ ", rating=" + rating + ", year=" + year + ", runtime=" + runtime + ", rated=" + rated
				+ ", globalRelease=" + globalRelease + ", country=" + country + ", otherRelease=" + otherRelease
				+ ", genres=" + genres + ", reviews=" + reviews + "]";
	}
	
	
}
