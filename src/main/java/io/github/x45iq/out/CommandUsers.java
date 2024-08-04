package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Role;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Класс команды users
 */
public class CommandUsers {
    private final UserDatabase userDatabase;
    private final Output output;
    private static final Pattern FILTER_REGEX = Pattern.compile("--filter-(role|name|contact|transactionsCount) (\\S+)");
    private static final Pattern ORDERED_BY_REGEX = Pattern.compile("--ordered-by (transactionsCount) (\\S+)");

    public CommandUsers(Output output,UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
        this.output = output;
    }

    /**
     * Выводит информацию о пользователях
     * @param command
     */
    public void users(String command){
        var stream = userDatabase.readAll().entrySet().stream().filter(s->s.getValue().getRole().getPriority()<= Role.MANAGER.getPriority());
        Matcher matcher = FILTER_REGEX.matcher(command);
        while(matcher.find()){
            String value = matcher.group(2);
            switch (matcher.group(1)){
                case "role"-> stream = stream.filter(entry->entry.getValue().getRole().equals(Role.fromString(value)));
                case "name"-> stream = stream.filter(entry->entry.getValue().getName().equals(value));
                case "contact"-> stream = stream.filter(entry->entry.getValue().getContact().equals(value));
                case "transactionsCount"-> stream = stream.filter(entry->String.valueOf(entry.getValue().getTransactionsCount()).equals(value));
                default -> {
                    output.println("Неверный формат запроса");
                    return;
                }
            }
        }
        matcher = ORDERED_BY_REGEX.matcher(command);
        if(matcher.find()){
            stream = stream.sorted(Comparator.comparingInt(stringUserEntry -> stringUserEntry.getValue().getTransactionsCount()));
        }
        stream.forEach(entry-> output.printf("id=%s %s\n",entry.getKey(),entry.getValue().toStringForPrint()));
    }
}
