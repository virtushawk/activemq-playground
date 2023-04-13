package org.virtushawk.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.virtushawk.activemq.service.sender.PubSubSender;

@SpringBootApplication
public class ActivemqPlaygroundApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ActivemqPlaygroundApplication.class, args);

		//task 1: Pub-Sub model with durable and non-durable subscriber.
		PubSubSender pubSubSender = context.getBean(PubSubSender.class);
		pubSubSender.sendMessage("Hello!");
	}

}
