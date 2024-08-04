package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;

import java.util.regex.Matcher;

/**
 * Класс команды delete_car
 */
public class CommandDeleteCar {
    private final Output output;
    private final MarketDatabase marketDatabase;
    private static final Logger LOGGER = Logger.getInstance();

    public CommandDeleteCar(Output output, MarketDatabase marketDatabase) {
        this.output = output;
        this.marketDatabase = marketDatabase;
    }

    /**
     * Удаляет машину из базы
     * @param command
     */
    public void delete(String command){
        Matcher matcher =  Command.DELETE_CAR.getPattern().matcher(command);
        matcher.find();
        long id = Long.parseLong(matcher.group(1));
        if(marketDatabase.delete(id)){
            output.println("Успешно удалено");
            LOGGER.log("Удалена машина, id=%s".formatted(id));

        }else{
            output.println("Машины с таким id не существует");
        }
    }
}
