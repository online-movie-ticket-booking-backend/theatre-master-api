package org.example.theatre.master.configuration;

import org.example.movie.core.common.schedule.TheatreDetailsResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaTemplateConfiguration {

    @Bean
    public KafkaTemplate<String, TheatreDetailsResponse> kafkaTheatreDetailsReplyTemplate(ProducerFactory<String,
            TheatreDetailsResponse> producerFactoryTheatreDetailsResponse) {
        return new KafkaTemplate<>(producerFactoryTheatreDetailsResponse);
    }
}