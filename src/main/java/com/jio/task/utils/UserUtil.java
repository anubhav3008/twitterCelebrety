package com.jio.task.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.jio.task.client.TwitterUtil;

import twitter4j.Trend;
import twitter4j.User;

@Component
public class UserUtil {
	@Autowired TwitterUtil twitterUtil;
	@Autowired Environment environment;
	public List<User> getUsers(List<Trend> trendList){
		List<User> userList= new ArrayList<>();
		for(Trend trend:  trendList) {
			List<User> users=twitterUtil.getUsersByTrend(trend);
			for(User user: users) {
				if(user.getFollowersCount()>Integer.valueOf(environment.getProperty("celeberity.definition.follower.count.min")))
					userList.add(user);
			}
		}
		return userList;
	}
}
