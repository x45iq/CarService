package io.github.x45iq.out;

import io.github.x45iq.logger.Log;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс команды save_logs
 */
public class CommandSaveLogs {
    private final Output output;
    private static final Logger LOGGER = Logger.getInstance();

    public CommandSaveLogs(Output output) {
        this.output = output;
    }

    /**
     * Записывает все логи в заданый файл
     * @param command
     */
    public void logs(String command){
        var logs = LOGGER.readAll();
        Matcher matcher =  Command.SAVE_LOGS.getPattern().matcher(command);
        matcher.find();
        String fileName = matcher.group(1);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){
            for(Log log : logs){
                writer.write(log.toStringForPrint());
            }
            output.printf("Сохранено в файл %s\n", fileName);
        }catch (IOException io){
            throw new RuntimeException(io);
        }
    }
}
