package org.example.theatre.master.configuration;

import lombok.Data;

@Data
public class ExchangeConfiguration {
  private String exchange;
  private String routingKey;
  private String queue;
}
