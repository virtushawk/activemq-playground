package org.virtushawk.activemq.service.receiver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Controller;
import org.virtushawk.activemq.service.sender.RequestReplySender;

@Controller
public class RequestReplyReceiver {

    private static final Logger logger = LogManager.getLogger(RequestReplyReceiver.class);

    @JmsListener(destination = RequestReplySender.REQUEST_REPLY_QUEUE,
            containerFactory = "queueJmsListenerContainerFactory")
    public String receive(String message) {
        logger.info("Received: {}", message);
        return "Hello from Receiver";
    }
}
