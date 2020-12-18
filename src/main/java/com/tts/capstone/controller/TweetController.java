package com.tts.capstone.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.capstone.model.SearchRequest;

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
		SearchRequest searchRequest= new SearchRequest();
		model.addAttribute("searchRequest", searchRequest);
		return "index";
	}
	
	@PostMapping("/search")
	public String getSearchResults(Model model, SearchRequest searchRequest) {
		ArrayList<String> profPics = new ArrayList<String>();
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
            	profPics.add(profPic);
            }
            else {
            	profPics.add("localURL to no profile pic pic");
            }
            
            
            
	}
		for(String p: profPics) {
			System.out.println(p);
		}
		model.addAttribute("profilePicArray", profPics);
		return "index"; 
		
		
	       
}

}
