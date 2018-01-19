package com.jio.task.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.jio.task.client.TwitterUtil;

import twitter4j.Location;
@Component
public class LocationUtil {
	 private static final Logger logger = Logger.getLogger(LocationUtil.class);
	public List<Location> locationList= new ArrayList<>();
	private @Autowired Environment environment;
	@Autowired TwitterUtil twitterUtil;
	public void setLocationList() {
		String LatLongList[]=environment.getProperty("lat-long").split(",");
		for(String latLong: LatLongList) {
			String[] coordinate=latLong.split(":");
			double latitude= Double.valueOf(coordinate[0]);
			double longitude= Double.valueOf(coordinate[1]);
			locationList.addAll( twitterUtil.getLocations(latitude, longitude));
		}
		logger.info("location size="+locationList.size());
		
	}
}
