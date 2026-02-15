package com.banco.auth.infrastructure.adapter.in.logger;

import com.banco.auth.domain.logger.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class GlobalLoggerAdapter implements Logger {

    @Override
    public void info(String message) {
        log.info(message);
    }

    @Override
    public void info(String message, String body) {
        log.info("{} - {}", message, body);
    }

    @Override
    public void info(String message, UUID id) {
        log.info("{} - {}", message, id);
    }

    @Override
    public void warn(String message) {
        log.warn(message);
    }

    @Override
    public void warn(String message, UUID id) {
        log.warn("{} - {}", message, id);
    }

    @Override
    public void warn(String message, String body) {
        log.warn("{} - {}", message, body);
    }

    @Override
    public void error(String message, Throwable exception) {
        log.error(message, exception);
    }

    @Override
    public void error(String message) {
        log.error(message);
    }

    @Override
    public void error(String message, String body, Throwable exception) {
        log.error("{} - {} - {}", message, body, exception);
    }

    @Override
    public void debug(String message, UUID id) {
        log.debug(message, id);
    }

    @Override
    public void debug(String message) {
        log.debug(message);
    }
}
