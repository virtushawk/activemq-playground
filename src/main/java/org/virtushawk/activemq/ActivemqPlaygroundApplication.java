package org.virtushawk.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.virtushawk.activemq.service.sender.PubSubSender;
import org.virtushawk.activemq.service.sender.RequestReplySender;
import org.virtushawk.activemq.service.sender.VirtualTopicSender;

import javax.jms.JMSException;

@SpringBootApplication
public class ActivemqPlaygroundApplication {

	public static void main(String[] args) throws JMSException {
		ApplicationContext context = SpringApplication.run(ActivemqPlaygroundApplication.class, args);

		//task 1: Pub-Sub model with durable and non-durable subscriber
		PubSubSender pubSubSender = context.getBean(PubSubSender.class);
		pubSubSender.sendMessage("Hello!");

		//task 2: Request-reply interaction using point-to-point model
		RequestReplySender requestReplySender = context.getBean(RequestReplySender.class);
		requestReplySender.sendAndReceiveMessage("Hello!");

		//task 3: Virtual topic model with two subscribers. Each one will receive 5 messages
		VirtualTopicSender virtualTopicSender = context.getBean(VirtualTopicSender.class);
		virtualTopicSender.sendMessageTimes(10, "Hello!");
	}

}
