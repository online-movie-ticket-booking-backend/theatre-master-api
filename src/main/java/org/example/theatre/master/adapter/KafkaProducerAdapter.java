package org.example.theatre.master.adapter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.core.common.schedule.TheatreDetailsRequest;
import org.example.movie.core.common.schedule.TheatreDetailsResponse;
import org.example.theatre.master.service.TheatreMasterService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaProducerAdapter {

    private TheatreMasterService theatreMasterService;

    @KafkaListener(topics = "${kafka.movieBookingApi.theatreDetails.topic.request}",
            containerFactory = "movieTheatreDetailsRequestListenerContainerFactory",
            groupId = "${kafka.movieBookingApi.groupName}"
    )
    @SendTo()
    public TheatreDetailsResponse receive(
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String uniqueId,
            TheatreDetailsRequest request) throws ExecutionException, InterruptedException, TimeoutException {
        log.info("Received Message with : {}",uniqueId);
        return theatreMasterService.fetchTheatreDetails(request);
    }
}

