package com.jio.task.client;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
@Component
public class TwitterUtil {
	 private static final Logger logger = Logger.getLogger(TwitterUtil.class);
	 private @Autowired Environment environment;
	Twitter twitter;
	public void instantiateTwitterClient() {
	ConfigurationBuilder cf = new ConfigurationBuilder();
	cf.setDebugEnabled(true)
			.setOAuthConsumerKey(environment.getProperty("auth.key"))
			.setOAuthConsumerSecret(environment.getProperty("auth.secret"))
			.setOAuthAccessToken(environment.getProperty("auth.access.token"))
			.setOAuthAccessTokenSecret(environment.getProperty("auth.access.token.secret"));
    
	TwitterFactory tf = new TwitterFactory(cf.build());
	 twitter= tf.getInstance();
	}
	
	public List<Location> getLocations(double latitude, double longitude) {
		GeoLocation geolocation = new GeoLocation(latitude, longitude);
		List<Location> locations = null;
		try {
			locations = twitter.getClosestTrends(geolocation);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return locations;
	}
	
	public Trends getTrends(Location location) {
		Trends trends=null;
		try {
			trends=twitter.getPlaceTrends(location.getWoeid());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return trends;
	}
	
	public List<User> getUsersByTrend(Trend trend){
		List<User> list=null;
		try {
			list=twitter.searchUsers(trend.getQuery(), 1);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return list;
	}

}
