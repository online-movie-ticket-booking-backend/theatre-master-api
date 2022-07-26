package org.example.theatre.master.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.example.movie.core.common.schedule.TheatreDetails;
import org.example.movie.core.common.schedule.TheatreDetailsRequest;
import org.example.movie.core.common.schedule.TheatreDetailsResponse;
import org.example.theatre.master.repository.TheatreMasterRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Data
@Slf4j
@Service
@AllArgsConstructor
public class TheatreMasterService {

  private final TheatreMasterRepository theatreMasterRepository;

  public TheatreDetailsResponse fetchTheatreDetails(TheatreDetailsRequest theatreDetailsRequest) {
    return TheatreDetailsResponse
            .of()
            .setTheatreDetailsMap(theatreMasterRepository
        .findAllByTheatreUniqueIdIn(theatreDetailsRequest.getTheatreUniqueIdList())
            .stream()
            .map(theatreMaster ->
                    TheatreDetails
                            .of()
                            .setTheatreName(
                                    theatreMaster
                                            .getTheatreName())
                                            .setTheatreUniqueId(theatreMaster.getTheatreUniqueId()))
            .collect(Collectors
                    .toMap(TheatreDetails::getTheatreUniqueId,theatreDetails -> theatreDetails)));
  }
}
