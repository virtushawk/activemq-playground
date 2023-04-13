package org.virtushawk.activemq.service.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.virtushawk.activemq.service.sender.PubSubSender;

@Controller
public class PubSubReceiverTwo {
    private static final Logger LOGGER = LogManager.getLogger(PubSubReceiverTwo.class);

    @JmsListener(destination = PubSubSender.PUB_SUB_DESTINATION, containerFactory = "durableTopicJmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("{} received message: {}", PubSubReceiverTwo.class, message);
    }
}
