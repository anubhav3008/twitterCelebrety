package com.jio.task.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.jio.task.client.TwitterUtil;

import twitter4j.Location;
import twitter4j.Trend;
import twitter4j.Trends;

@Component
public class TrendsUtil {
	private static final Logger logger = Logger.getLogger(TrendsUtil.class);
	@Autowired TwitterUtil twitterUtil;
	@Autowired Environment environment;
	public List<Trend> getTrends(List<Location> locationList) {
		
		logger.info("going to fetch trends for locations "+locationList.size());
		List<Trend> trendList= new ArrayList<>();
		for(Location location:  locationList) {
		   logger.info("going to fetch for "+ location.getCountryName());
		   Trends trends= twitterUtil.getTrends(location);
		   for(Trend trend:  trends.getTrends()) {
			   if(trendList.size()>Integer.valueOf(environment.getProperty("celeb.defintion.top.trend")))
				   break;
			   trendList.add(trend);
		   }
		}//need to improve logic when two counties are searched , in sch cases only the first will be picked up.
		logger.info("total number of trends fetched  = "+trendList.size());
		logger.info("trends= "+trendList);
		return trendList;
	}

}
