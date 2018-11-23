package com.niftysoft.example.model.dto;

import lombok.Value;

/**
 * Interface used to retrieve Widget IDs only.
 * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.dtos
 */
@Value
public class IdDto {
    Long id;
}
