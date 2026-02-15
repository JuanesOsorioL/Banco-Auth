package com.banco.auth.domain.logger;

import java.util.UUID;

public interface Logger {
    void info(String message);

    void info(String message, String body);

    void info(String message, UUID body);

    void warn(String message);

    void warn(String message, String body);

    void warn(String message, UUID body);

    void error(String message, Throwable exception);

    void error(String message);

    void error(String message, String body, Throwable exception);

    void debug(String message, UUID id);
    void debug(String message);
}
