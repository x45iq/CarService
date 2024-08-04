package io.github.x45iq.logger;

import io.github.x45iq.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record Log(String message, String userId, LocalDate date) {
    public String toStringForPrint() {
        return "message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", date=" + date;
    }
}
