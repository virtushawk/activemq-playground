package org.virtushawk.activemq.service.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;

@Controller
public class VirtualTopicReceiverTwo {

    private static final Logger LOGGER = LogManager.getLogger(VirtualTopicReceiverTwo.class);

    @JmsListener(destination = "Consumer.virtual.VirtualTopic.SimpleTopic",
            containerFactory = "queueJmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("Received: {}", message);
    }
}
