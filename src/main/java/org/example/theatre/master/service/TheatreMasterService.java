package org.example.theatre.master.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.theatre.master.dto.mq.TheatreDetailsRequest;
import org.example.theatre.master.dto.mq.TheatreDetailsResponse;
import org.example.theatre.master.repository.TheatreMasterRepository;
import org.springframework.stereotype.Service;

@Data
@Slf4j
@Service
@AllArgsConstructor
public class TheatreMasterService {

  private final TheatreMasterRepository theatreMasterRepository;

  public TheatreDetailsResponse fetchTheatreDetails(TheatreDetailsRequest theatreDetailsRequest) {
    return theatreMasterRepository
        .findByTheatreUniqueId(theatreDetailsRequest.getTheatreUniqueId())
        .map(
            theatreMaster ->
                TheatreDetailsResponse.of().setTheatreName(theatreMaster.getTheatreName()))
        .orElseGet(TheatreDetailsResponse::of);
  }
}
