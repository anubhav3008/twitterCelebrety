package com.jio.task.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.task.client.SlackUtil;

import twitter4j.User;

@Component
public class MessageUtil {
	private static final Logger logger = Logger.getLogger(MessageUtil.class);
	@Autowired SlackUtil slackUtil;
	public void sendMessage(List<User> userList) {
		StringBuffer message= new StringBuffer();
		
		for(User user: userList) {
			
			message.append(user.getName());
			message.append(":");
			message.append(user.getFollowersCount());
			message.append("\n");
		}
		slackUtil.sendMessage(message.toString());
	}
}


