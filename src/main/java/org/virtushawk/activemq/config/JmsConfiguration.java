package org.virtushawk.activemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;
import java.util.List;

@EnableJms
@Configuration
public class JmsConfiguration {

    @Value("${spring.activemq.user}")
    private String username;

    @Value("${spring.activemq.password}")
    private String password;

    @Value("${spring.activemq.broker-url}")
    private String brokerURL;

    @Bean
    public ConnectionFactory activeMQConnectionFactory() {
     ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
     activeMQConnectionFactory.setTrustedPackages(List.of("org.virtushawk.activemq"));

        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory(activeMQConnectionFactory);
        singleConnectionFactory.setReconnectOnException(true);
        singleConnectionFactory.setClientId("activeMqClientId");
        return singleConnectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory durableTopicJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());
        factory.setSubscriptionDurable(true);
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory queueJmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory());
        factory.setMessageConverter(jacksonJmsMessageConverter());
        return factory;
    }

    @Bean
    public JmsTemplate topicTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

    @Bean
    public JmsTemplate queueTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
        return jmsTemplate;
    }
    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }
}
