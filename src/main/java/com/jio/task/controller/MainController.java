package com.jio.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jio.task.job.CelecbrityJob;

@Controller
public class MainController {
	@Autowired CelecbrityJob celebrityJob;
	@RequestMapping("/healthCheck")
	public @ResponseBody String healthCheck() {
		return "healthCheck working fine";
	}
	
	@RequestMapping("/celebJob")
	public @ResponseBody String getCelebs(){
		celebrityJob.getCelebs();
		return "true";
	}

}
