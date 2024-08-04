package io.github.x45iq.logger;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LoggerTest {
    private final Logger logger = Logger.getInstance();
    {
        Logger.setUserId("test");
    }
    @Test
    void logAndReadAll() {
        logger.log("test");
        assertThat(logger.readAll()).contains(new Log("test","test", LocalDate.now()));
    }

    @Test
    void getInstance() {
        assertThat(logger).isEqualTo(Logger.getInstance());
    }
}