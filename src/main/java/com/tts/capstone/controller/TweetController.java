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
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private ArrayList<String> profPics;
	private ArrayList<String> tweets;
	private ArrayList<String> userNames;
	private ArrayList<String> shuffled;
	
	@GetMapping("/")
    public String getMainPage(Model model){
		SearchRequest searchRequest= new SearchRequest();
		model.addAttribute("searchRequest", searchRequest);
		return "index";
	}
	
	@PostMapping("/search")
	public String getSearchResults(Model model, SearchRequest searchRequest) {
		ArrayList<String>[] picsAndTweets = twitterService.getProfPics(searchRequest);
		profPics = picsAndTweets[0];
		tweets = picsAndTweets[1];
		userNames = picsAndTweets[2];
		shuffled = new ArrayList<String>(tweets);
		Collections.shuffle(shuffled);		
		model.addAttribute("profilePicArray", profPics);
		model.addAttribute("tweetArray", tweets);
		model.addAttribute("userNameArray", userNames);
		model.addAttribute("shuffledTweets", shuffled);
		
		
		return "index"; 	
	       
}
	
	@PostMapping("/checkQuiz")
	public String checkQuiz(@RequestParam(value="guessNum", required=false) String guessNum, Model model) {
		String[] guesses = guessNum.split(",");
		System.out.println("Original Tweets in order:");
		for(String s : this.tweets) {
			System.out.println(s);
		}
		System.out.println("Shuffled Tweets on page:");
		for(String r : this.shuffled) {
			System.out.println(r);
		}
		
		ArrayList<String> guessesAsStrings = new ArrayList<String>();
		for(String s: guesses) {
			int i = Integer.parseInt(s);
			i = i-1; //to make sure we actually look at 0-4, not 1-5
			String t = shuffled.get(i);
			guessesAsStrings.add(t);
		}
		int numCorrect = 0;
		for(int i = 0; i<guessesAsStrings.size(); i++) {
			if(tweets.get(i).equals(guessesAsStrings.get(i))){
				numCorrect++;
			}
		}
		model.addAttribute("numCorrect", numCorrect);
		return "checkQuiz";
	}
	
}
