package io.github.x45iq.logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    private static final List<Log> logs = new ArrayList<>();
    private static Logger instance = null;
    private static String userId = null;
    private Logger(){

    }
    public void log(String message){
        logs.add(new Log(message, userId, LocalDate.now()));
    }

    public static void setUserId(String userId) {
        Logger.userId = userId;
    }
    public List<Log> readAll(){
        return new ArrayList<>(logs);
    }

    public static Logger getInstance() {
        if(instance == null){
            instance = new Logger();
        }
        return instance;
    }
}
