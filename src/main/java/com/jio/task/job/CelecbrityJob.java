package com.jio.task.job;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.task.utils.LocationUtil;
import com.jio.task.utils.MessageUtil;
import com.jio.task.utils.TrendsUtil;
import com.jio.task.utils.UserUtil;

import twitter4j.Trend;
import twitter4j.User;

@Component
public class CelecbrityJob {
	private static final Logger logger = Logger.getLogger(CelecbrityJob.class);
	@Autowired TrendsUtil trendsUtils;
	@Autowired LocationUtil locationUtil;
	@Autowired UserUtil userUtil;
	@Autowired MessageUtil messageUtil;
	public void getCelebs() {
		List<Trend> trendsList=  trendsUtils.getTrends(locationUtil.locationList);
		List<User> userList=userUtil.getUsers(trendsList);
		messageUtil.sendMessage(userList);
	}
}
