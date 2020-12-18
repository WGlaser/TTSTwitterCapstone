package com.tts.capstone.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.auth.AccessToken;


@Controller
public class TweetController {

	@Autowired 
	private Twitter twitter;
	
	@GetMapping("/")
    public String getMainPage(Model model){
		Query query = new Query();
	       //querry near home address query.geoCode(new GeoLocation(35.222860,-80.770590),10.0,Query.MILES);
	        query.query("Boston Celtics");
	        QueryResult result = null;
			try {
				result = twitter.search(query);
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        for (Status status : result.getTweets()) {
	            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText() );
	            String profPic = status.getUser().get400x400ProfileImageURL();
	            if(profPic !=null) {
	            	System.out.println("Profpic= "+ profPic);
	            }
	            else {
	            	System.out.println("no profloc");
	            }
     
    }
	        return "index";
}
}
