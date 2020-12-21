package com.tts.capstone.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.capstone.model.SearchRequest;
import com.tts.capstone.service.TwitterService;

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
	
	@Autowired
	private TwitterService twitterService;
	
	@GetMapping("/")
    public String getMainPage(Model model){
		SearchRequest searchRequest= new SearchRequest();
		model.addAttribute("searchRequest", searchRequest);
		return "index";
	}
	
	@PostMapping("/search")
	public String getSearchResults(Model model, SearchRequest searchRequest) {
		ArrayList<String>[] picsAndTweets = twitterService.getProfPics(searchRequest);
		ArrayList<String> profPics = picsAndTweets[0];
		ArrayList<String> tweets = picsAndTweets[1];
		ArrayList<String> userNames = picsAndTweets[2];
		ArrayList<String> shuffled = new ArrayList<String>(tweets);
		Collections.shuffle(shuffled);		
		model.addAttribute("profilePicArray", profPics);
		model.addAttribute("tweetArray", tweets);
		model.addAttribute("userNameArray", userNames);
		model.addAttribute("shuffledTweets", shuffled);
		
		
		return "index"; 
		/*ArrayList<String> profPics = new ArrayList<String>();
		Query query = new Query();
		query.query(searchRequest.getSearchTerms());
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
            	if(!profPics.contains(profPic)) {
            		profPics.add(profPic);
            	}
            	 else {
                 	profPics.add("no");
                 }
            }
            else {
            	profPics.add("no");
            }
            
	}
		for(String p: profPics) {
			System.out.println(p);
		}*/
		
		
		
		
	       
}
	
	public  ArrayList<String> scrambleTweets(ArrayList<String> arr){
		ArrayList<String> shuffled = arr;
		Collections.shuffle(shuffled);
		return shuffled;
	}

}
