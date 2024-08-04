package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.Order;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс команды logs
 */
public class CommandLogs {
    private final Output output;
    private static final Logger LOGGER = Logger.getInstance();
    private static final Pattern FILTER_REGEX = Pattern.compile("--filter-(date|userId) (\\S+)");

    public CommandLogs(Output output) {
        this.output = output;
    }

    /**
     * Выводит логи в консоль
     * @param command
     */
    public void logs(String command){
        var stream = LOGGER.readAll().stream();
        Matcher matcher = FILTER_REGEX.matcher(command);
        while(matcher.find()){
            String value = matcher.group(2);
            switch (matcher.group(1)){
                case "date"-> stream = stream.filter(entry->entry.date().equals(LocalDate.parse(value, DateTimeFormatter.ISO_DATE)));
                case "userId"-> stream = stream.filter(entry->entry.userId().equals(value));
                default -> {
                    output.println("Неверный формат запроса");
                    return;
                }
            }
        }
        stream.forEach(log -> output.println(log.toStringForPrint()));
    }
}
