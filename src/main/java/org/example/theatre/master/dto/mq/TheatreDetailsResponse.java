package org.example.theatre.master.dto.mq;

import lombok.Data;
import lombok.experimental.Accessors;

@Data(staticConstructor = "of")
@Accessors(chain = true)
public class TheatreDetailsResponse {
  private String theatreName;
}
