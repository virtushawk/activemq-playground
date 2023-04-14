package org.virtushawk.activemq.service.sender;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class VirtualTopicSender {

    public static final String VIRTUAL_TOPIC = "VirtualTopic.SimpleTopic";

    private final JmsTemplate jmsTemplate;

    public VirtualTopicSender(@Qualifier(value = "topicTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessageTimes(int times, String message) {
        for (int i = 0; i < times; i++) {
            jmsTemplate.convertAndSend(VIRTUAL_TOPIC, message);
        }
    }
}
