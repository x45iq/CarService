package io.github.x45iq.out;

import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Car;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс команды edit_user
 */
public class CommandEditUser {
    private final Output output;
    private final UserDatabase userDatabase;
    private static final Pattern FIELD_REGEX = Pattern.compile("--field-(name|contact) (\\S+)");
    private static final Logger LOGGER = Logger.getInstance();

    public CommandEditUser(Output output, UserDatabase userDatabase) {
        this.output = output;
        this.userDatabase = userDatabase;
    }

    /**
     * Изменяет информацию о пользователе
     * @param command
     */
    public void edit(String command){
        Matcher matcher =  Command.EDIT_USER.getPattern().matcher(command);
        matcher.find();
        String id = matcher.group(1);
        matcher = FIELD_REGEX.matcher(command);
        User user = userDatabase.read(id);
        if(user==null){
            output.println("Человека с таким id не существует");
            return;
        }
        String name = user.getName(),contact = user.getContact();
        while(matcher.find()){
            String value = matcher.group(2);
            switch (matcher.group(1)){
                case "name"-> name = value;
                case "contact"-> contact = value;
                default -> {
                    output.println("Неверный формат запроса");
                    return;
                }
            }
        }
        user.setName(name);
        user.setContact(contact);
        userDatabase.update(id,user);
        output.println("Данные успешно отредактированы");
        LOGGER.log("Данные о пользователе изменены");
    }
}
