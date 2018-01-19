package com.jio.task.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jio.task.client.TwitterUtil;

@Component
public class Innitialization {
	@Autowired LocationUtil locationUtil;
	@Autowired TwitterUtil twitterUtil;
	@PostConstruct
	public void innitalizer() {
		twitterUtil.instantiateTwitterClient();
		locationUtil.setLocationList();
	}

}
