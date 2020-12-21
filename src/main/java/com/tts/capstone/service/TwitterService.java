package com.tts.capstone.service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tts.capstone.model.SearchRequest;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Service
public class TwitterService {
	@Autowired 
	private Twitter twitter;
	
	public ArrayList<String>[] getProfPics(SearchRequest searchRequest){
		ArrayList<String>[] picsAndTweetsAndNames = new ArrayList[3];
		ArrayList<String> profPics = new ArrayList<String>();
		ArrayList<String> tweets = new ArrayList<String>();
		ArrayList<String> userNames = new ArrayList<String>();
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
			if(profPics.size()==5) {
				break;
			}
         //   System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText() );
            String profPic = status.getUser().get400x400ProfileImageURL();
            tweets.add(status.getText());
            userNames.add(status.getUser().getScreenName());
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
		
		picsAndTweetsAndNames[0]= profPics;
		picsAndTweetsAndNames[1] = tweets;
		picsAndTweetsAndNames[2] = userNames;
		return picsAndTweetsAndNames;
	}
	
	
	
	
}
