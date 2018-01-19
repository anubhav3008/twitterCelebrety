package com.jio.task.client;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.github.seratch.jslack.api.webhook.WebhookResponse;
import com.jio.task.job.CelecbrityJob;


@Component
public class SlackUtil {
	
	@Autowired Environment environment;
	private static final Logger logger = Logger.getLogger(SlackUtil.class);

	Slack slack = Slack.getInstance();
	public void sendMessage(String message) {
		String url = environment.getProperty("slack.url");
		Payload payload = Payload.builder()
				  .channel("#random")
				  .username("jSlack Bot")
				  .iconEmoji(":smile_cat:")
				  .text(message)
				  .build();
		
		try {
			WebhookResponse response = slack.send(url, payload);
			logger.info(response.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}


		
	}

}
