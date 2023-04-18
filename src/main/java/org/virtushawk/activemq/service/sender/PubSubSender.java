package org.virtushawk.activemq.service.sender;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class PubSubSender {

    public static final String PUB_SUB_DESTINATION = "pub-sub-topic";

    private final JmsTemplate template;

    public PubSubSender(@Qualifier("topicTemplate") JmsTemplate template) {
        this.template = template;
    }

    public void sendMessage(String message) {
        template.convertAndSend(PUB_SUB_DESTINATION, message);
    }
}
