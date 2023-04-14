package org.virtushawk.activemq.service.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;


@Service
public class RequestReplySender {

    private static final Logger LOGGER = LogManager.getLogger(RequestReplySender.class);

    public static final String REQUEST_REPLY_QUEUE = "request-reply-queue";

    private final JmsTemplate jmsTemplate;
    private final MessageConverter messageConverter;

    public RequestReplySender(@Qualifier(value = "queueTemplate") JmsTemplate jmsTemplate,
                              MessageConverter messageConverter) {
        this.jmsTemplate = jmsTemplate;
        this.messageConverter = messageConverter;
    }

    public void sendAndReceiveMessage(String message) throws JMSException {
        Message response = jmsTemplate.sendAndReceive(REQUEST_REPLY_QUEUE,
                session -> messageConverter.toMessage(message, session));

        LOGGER.info("Sent: {} and received: {}", message, messageConverter.fromMessage(response));
    }
}
