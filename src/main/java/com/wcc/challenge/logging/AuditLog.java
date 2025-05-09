package com.wcc.challenge.logging;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class AuditLog {

    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Long executionTime;
    private String action;
    private Object request;

}
