package org.example.theatre.master.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    @Value("${kafka.movieBookingApi.theatreDetails.topic.response}")
    private String theatreDetailsTopicName;


    @Bean
    public NewTopic topicTheatreDetailsTopicName() {
        return TopicBuilder.name(theatreDetailsTopicName).build();
    }


}