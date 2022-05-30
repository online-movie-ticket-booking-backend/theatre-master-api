package org.example.theatre.master.configuration;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("message")
public class TheatreMasterMQConfiguration {

  private MessageConfiguration messageConfiguration;
  private ExchangeConfiguration theatreDetailsExchangeConfiguration;

  @Bean
  public Exchange theatreDetailsExchange() {
    return ExchangeBuilder.topicExchange(theatreDetailsExchangeConfiguration.getExchange()).build();
  }

  @Bean
  public Queue theatreDetailsQueue() {
    return new Queue(theatreDetailsExchangeConfiguration.getQueue(), true);
  }

  @Bean
  public Binding theatreDetailsBinding() {
    return BindingBuilder.bind(theatreDetailsQueue())
        .to(theatreDetailsExchange())
        .with(theatreDetailsExchangeConfiguration.getRoutingKey())
        .noargs();
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public CachingConnectionFactory connectionFactory() {
    CachingConnectionFactory cachingConnectionFactory =
        new CachingConnectionFactory(messageConfiguration.getHost());
    cachingConnectionFactory.setUsername(messageConfiguration.getUsername());
    cachingConnectionFactory.setPassword(messageConfiguration.getPassword());
    cachingConnectionFactory.setPort(messageConfiguration.getPort());
    cachingConnectionFactory.setVirtualHost(messageConfiguration.getVirtualHost());
    return cachingConnectionFactory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(jsonMessageConverter());
    rabbitTemplate.setReplyAddress("movieDetailsQueue");
    return rabbitTemplate;
  }
}
