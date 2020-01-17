package com.example.demo;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.ullink.slack.simpleslackapi.SlackChannel;
import com.ullink.slack.simpleslackapi.SlackSession;
import com.ullink.slack.simpleslackapi.impl.SlackSessionFactory;

@SpringBootApplication
public class SpringbootSlackSampleApplication {

	public static void main(String[] args) {
		//		SpringApplication.run(SpringbootSlackSampleApplication.class, args);

		try (ConfigurableApplicationContext ctx = SpringApplication.run(SpringbootSlackSampleApplication.class, args)) {
			SpringbootSlackSampleApplication app = ctx.getBean(SpringbootSlackSampleApplication.class);
			app.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			// BotのAPI Tokenを設定
			SlackSession session = SlackSessionFactory.createWebSocketSlackSession("xoxb-****"); // FIXME

			session.connect();

			SlackChannel channel = session.findChannelByName("test-channel");
			String message = "こんにちは";
			session.sendMessage(channel, message);

			session.disconnect();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
