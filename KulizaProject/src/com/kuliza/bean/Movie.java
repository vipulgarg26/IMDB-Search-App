package com.kuliza.bean;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Movie {

	String title,urlIMDB,urlPoster,votes,rating,year,runtime,rated,globalRelease,country,indianRelease;
	List<String> genres;
	List<Review> reviews;

	
	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public String getIndianRelease() {
		return indianRelease;
	}

	public void setIndianRelease(String indianRelease) {
		this.indianRelease = indianRelease;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getGlobalRelease() {
		return globalRelease;
	}

	public void setGlobalRelease(String globalRelease) {
		this.globalRelease = globalRelease;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlIMDB() {
		return urlIMDB;
	}

	public void setUrlIMDB(String urlIMDB) {
		this.urlIMDB = urlIMDB;
	}

	public String getUrlPoster() {
		return urlPoster;
	}

	public void setUrlPoster(String urlPoster) {
		this.urlPoster = urlPoster;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public String getVotes() {
		return votes;
	}

	public void setVotes(String votes) {
		this.votes = votes;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getRuntime() {
		return runtime;
	}

	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}

	@Override
	public String toString() {
		return "Movie [title=" + title + ", urlIMDB=" + urlIMDB + ", urlPoster=" + urlPoster + ", votes=" + votes
				+ ", rating=" + rating + ", year=" + year + ", runtime=" + runtime + ", rated=" + rated
				+ ", globalRelease=" + globalRelease + ", country=" + country + ", indianRelease=" + indianRelease
				+ ", genres=" + genres + ", reviews=" + reviews + "]";
	}
	
	
}
