package io.github.x45iq.in;

import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;

/**
 * Класс отвечающий за аунтификацию пользователя
 */
public class Authenticator {
    private final UserDatabase userDatabase;
    private final Input input;
    public enum AuthType {LOG_IN,SIGN_IN}
    private static final Logger LOGGER = Logger.getInstance();
    public Authenticator(UserDatabase userDatabase,Input input){
        this.userDatabase = userDatabase;
        this.input = input;
    }
    public User auth(AuthType authType){
        return switch (authType){
            case LOG_IN->{
                String login = input.readString("Введите логин: ",s->userDatabase.read(s)!=null);
                User user = userDatabase.read(login);
                input.readString("Введите пароль: ",s->user.comparePasswords(s.hashCode()));
                Logger.setUserId(login);
                LOGGER.log("Пользователь вошёл в систему");
                yield user;
            }
            case SIGN_IN->{
                String login = input.readString("Введите логин: ",s->userDatabase.read(s)==null);
                int hashPassword = input.readString("Введите пароль: ").hashCode();
                Role role = input.readString("Вы менеджер или клиент? [1/2] ", s -> s.equals("1") || s.equals("2")).equals("1")?Role.MANAGER:Role.CLIENT;
                User user = new User(role,hashPassword);
                userDatabase.create(login,user);
                Logger.setUserId(login);
                LOGGER.log("Пользователь зарегистрировался");
                yield user;
            }
        };
    }
}
