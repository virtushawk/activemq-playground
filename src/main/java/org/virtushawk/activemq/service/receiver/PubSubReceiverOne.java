package org.virtushawk.activemq.service.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.virtushawk.activemq.service.sender.PubSubSender;

@Controller
public class PubSubReceiverOne {

    private static final Logger LOGGER = LogManager.getLogger(PubSubReceiverOne.class);

    @JmsListener(destination = PubSubSender.PUB_SUB_DESTINATION, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String message) {
        LOGGER.info("{} received message: {}", PubSubReceiverOne.class, message);
    }
}
