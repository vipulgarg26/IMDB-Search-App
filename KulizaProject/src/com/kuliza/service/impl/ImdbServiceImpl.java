package com.kuliza.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.kuliza.bean.Movie;
import com.kuliza.bean.Review;
import com.kuliza.service.ImdbService;

@Service
@Transactional
public class ImdbServiceImpl implements ImdbService{

	Logger logger = Logger.getLogger(ImdbServiceImpl.class);
	@Override
	public JSONObject getImdbData(String searchUrl) {
		// TODO Auto-generated method stub
		
		BasicConfigurator.configure();
		JSONObject jsonObject=null;
		RestTemplate template = new RestTemplate();
		String searchData = template.getForObject(searchUrl,String.class);
		String filteredData = searchData.substring(1, searchData.length()-1);
		//logger.info(filteredData);
		JSONParser jsonParser = new JSONParser();
		Object object;
		try {
			object = jsonParser.parse(filteredData);
			jsonObject = (JSONObject)object;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	@Override
	public List<String> getMovieList(String actorUrl) {
		// TODO Auto-generated method stub
		
		List<String> movies = new ArrayList<>();
		Document document;
		try {
			document = Jsoup.connect(actorUrl).get();
			Elements links = document.select("div[id=knownfor] > div > a");
			
			for(Element link : links){
				if(!link.text().equals(""))
				movies.add(link.text());
			}
			movies.remove(movies.size()-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movies;
	}
	@Override
	public Movie getReviews(Movie movie) {
		// TODO Auto-generated method stub
		String indianRelease=null;
		int index=0;
		List<String> reviewDataList=new ArrayList<>();
		List<String> reviewHeadList=new ArrayList<>();
		List<String> reviewUsefulList=new ArrayList<>();
		List<Double> useMeterList = new ArrayList<>();
		List<Review> reviews = new ArrayList<>();
		Document documentReleaseDate,documentReview;
		try {
			documentReleaseDate=Jsoup.connect(movie.getUrlIMDB()).get();
			Elements link=documentReleaseDate.select("span.nobr > a[title=See all release dates]");
			indianRelease = link.text();   // Indian Release date
			logger.info(movie.getTitle());
			//logger.info(link.text()); 
			
			logger.info(indianRelease);
			String reviewUrl = "http://www.imdb.com/title/tt0109830" +"/reviews";
			//logger.info(reviewUrl);
			documentReview=Jsoup.connect(reviewUrl).userAgent("Chrome").get();
			Elements reviewUse = documentReview.select("div > small");
			Elements reviewHeading = documentReview.select("h2");
			Elements reviewBody = documentReview.select("div + p");
			reviewBody.remove(reviewBody.size()-1);
			//logger.info(reviewUse);
			//logger.info(reviewHeading);
			//logger.info(reviewBody);
			
			for(Element review : reviewBody){
				reviewDataList.add(review.text());
			}
			for(Element review : reviewHeading ){
				reviewHeadList.add(review.text());
			}
			
			for(Element review : reviewUse){
				reviewUsefulList.add(review.text());
			}
			for(index=0;index<reviewUsefulList.size();index=index+3){
				String[] splitter = reviewUsefulList.get(index).split("\\s+");
				//logger.info(Arrays.toString(splitter));
				int num1 = Integer.parseInt(splitter[0]);
				int num2 = Integer.parseInt(splitter[3]);
				double usePercent = (num1*100)/num2;
				//logger.info(usePercent);
				useMeterList.add(usePercent);
			}
			
			//logger.info(useMeter.size());
			//logger.info(reviewHeadList.size());
			//logger.info(reviewDataList.size());
			//logger.info(reviewBody.get(0).text());
			//logger.info(reviewDataList.get(8));
			
			for(index=0;index<useMeterList.size();index++){
				
				Review review = new Review();
				review.setData(reviewDataList.get(index));
				review.setHeading(reviewHeadList.get(index));
				review.setUsefulness(useMeterList.get(index));
				reviews.add(review);
			}
			
			/*logger.info(reviews.get(0).getUsefulness());
			logger.info(reviews.get(1).getUsefulness());
			logger.info(reviews.get(2).getUsefulness());*/
			
			Collections.sort(reviews);
			
			/*logger.info(reviews.get(0).getUsefulness());
			logger.info(reviews.get(1).getUsefulness());
			logger.info(reviews.get(2).getUsefulness());*/
			
			
			while(reviews.size()>3){
				reviews.remove(reviews.size()-1);
			}
			
			logger.info(reviews.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		movie.setIndianRelease(indianRelease);
		movie.setReviews(reviews);
		return movie;
	}
	
	

}
