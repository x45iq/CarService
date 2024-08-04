package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс команды car_list
 */
public class CommandCarList {
    private final Output output;
    private final MarketDatabase marketDatabase;
    private static final Pattern FILTER_REGEX = Pattern.compile("--filter-(id|brand|model|year|price) (\\S+)");

    public CommandCarList(Output output, MarketDatabase marketDatabase) {
        this.output = output;
        this.marketDatabase = marketDatabase;
    }

    /**
     * Выводит информацию о всех доступных машинах
     * @param command
     */
    public void list(String command){
        var stream = marketDatabase.readAll().entrySet().stream();
        Matcher matcher = FILTER_REGEX.matcher(command);
        while(matcher.find()){
            String value = matcher.group(2);
            switch (matcher.group(1)){
                case "brand"-> stream = stream.filter(entry->entry.getValue().brand().equals(value));
                case "model"-> stream = stream.filter(entry->entry.getValue().model().equals(value));
                case "year"-> stream = stream.filter(entry->String.valueOf(entry.getValue().year()).equals(value));
                case "id"-> stream = stream.filter(entry->String.valueOf(entry.getKey()).equals(value));
                default ->{
                    output.println("Неверный формат запроса");
                    return;
                }
            }
        }
        stream.forEach(entry-> output.printf("id=%s %s\n",entry.getKey(),entry.getValue().toStringForPrint()));
    }
}
