package com.wcc.challenge.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.ZonedDateTime;

@Aspect
@Component
@Slf4j
@AllArgsConstructor
public class AuditLoggingAspect {

    private final ObjectMapper objectMapper;

    @Around("@annotation(auditLogging)")
    public Object logApiRequest(ProceedingJoinPoint joinPoint, AuditLogging auditLogging)
            throws Throwable {
        ZonedDateTime start = ZonedDateTime.now();
        Object proceed = joinPoint.proceed();
        ZonedDateTime end = ZonedDateTime.now();
        Long executionTime = end.toInstant().toEpochMilli() - start.toInstant().toEpochMilli();
        Object request = ObjectUtils.isEmpty(joinPoint.getArgs()[0]) ? null : joinPoint.getArgs()[0];

        AuditLog auditLog = AuditLog.builder()
                .startTime(start)
                .endTime(end)
                .executionTime(executionTime)
                .action(auditLogging.action())
                .request(request)
                .build();

        log.info("{}", objectMapper.writeValueAsString(auditLog));
        return proceed;
    }

}
