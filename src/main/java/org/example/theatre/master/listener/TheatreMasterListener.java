package org.example.theatre.master.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.theatre.master.dto.mq.TheatreDetailsRequest;
import org.example.theatre.master.dto.mq.TheatreDetailsResponse;
import org.example.theatre.master.service.TheatreMasterService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class TheatreMasterListener {

  private final TheatreMasterService theatreMasterService;

  @RabbitListener(queues = "theatreDetailsQueue")
  public TheatreDetailsResponse receiveMovieDetailsMessage(
      TheatreDetailsRequest theatreDetailsRequest) {
    return theatreMasterService.fetchTheatreDetails(theatreDetailsRequest);
  }
}
