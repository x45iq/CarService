package io.github.x45iq;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.in.Authenticator;
import io.github.x45iq.in.Input;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.User;
import io.github.x45iq.out.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс приложения
 */
public class Application {
    private static final MarketDatabase MARKET_DATABASE = new MarketDatabase();
    private static final UserDatabase USER_DATABASE = new UserDatabase();
    private static final OrdersDatabase ORDERS_DATABASE = new OrdersDatabase();
    private static final Output OUTPUT = new Output(System.out);
    private static final Input INPUT = new Input(OUTPUT,System.in);
    private static final Authenticator AUTHENTICATOR = new Authenticator(USER_DATABASE, INPUT);
    private static final CommandHelp COMMAND_HELP = new CommandHelp(OUTPUT);
    private static final CommandCarList COMMAND_CAR_LIST = new CommandCarList(OUTPUT,MARKET_DATABASE);
    public static final CommandAddCar COMMAND_ADD_CAR = new CommandAddCar(OUTPUT,MARKET_DATABASE);
    public static final CommandUpdateCar COMMAND_UPDATE_CAR = new CommandUpdateCar(OUTPUT,MARKET_DATABASE);
    public static final CommandDeleteCar COMMAND_DELETE_CAR = new CommandDeleteCar(OUTPUT,MARKET_DATABASE);
    public static final CommandUsers COMMAND_USERS= new CommandUsers(OUTPUT,USER_DATABASE);
    public static final CommandEditUser COMMAND_EDIT_USER= new CommandEditUser(OUTPUT,USER_DATABASE);
    public static final CommandBuyCar COMMAND_BUY_CAR = new CommandBuyCar(OUTPUT,USER_DATABASE,ORDERS_DATABASE);
    public static final CommandOrders COMMAND_ORDERS= new CommandOrders(OUTPUT,ORDERS_DATABASE);
    public static final CommandCancelOrder COMMAND_CANCEL_ORDER = new CommandCancelOrder(OUTPUT,ORDERS_DATABASE);
    public static final CommandUpdateStatus COMMAND_UPDATE_STATUS = new CommandUpdateStatus(OUTPUT,ORDERS_DATABASE,MARKET_DATABASE);
    public static final CommandLogs COMMAND_LOGS = new CommandLogs(OUTPUT);
    public static final CommandSaveLogs COMMAND_SAVE_LOGS = new CommandSaveLogs(OUTPUT);

    /**
     * Точка входа
     * @param args
     */
    public static void main(String[] args) {
        OUTPUT.println("Добро пожаловать");
        while (true) {
            String type = INPUT.readString("Вход/Регистрация [1/2] ", (s -> s.equals("1") || s.equals("2")));
            User user = AUTHENTICATOR.auth(type.equals("1") ? Authenticator.AuthType.LOG_IN : Authenticator.AuthType.SIGN_IN);
            OUTPUT.print("Доступные для вас комманды: ");
            OUTPUT.println(Arrays.stream(Command.values()).filter(stringRoleEntry -> (stringRoleEntry.getMinimalRole().getPriority() <= user.getRole().getPriority())).map(Objects::toString).collect(Collectors.joining(", ")));
            OUTPUT.println("Для дополнительной информации: help [command]");
            loop: while (true){
                var pair = INPUT.readCommand("Введите комманду: ");
                if(pair.firstValue().getMinimalRole().getPriority() > user.getRole().getPriority()){
                    OUTPUT.println("Недостаточно прав");
                    continue;
                }
                switch (pair.firstValue()){
                    case EXIT -> {break loop;}
                    case HELP -> COMMAND_HELP.help(pair.secondValue());
                    case CAR_LIST -> COMMAND_CAR_LIST.list(pair.secondValue());
                    case ADD_CAR -> COMMAND_ADD_CAR.create(pair.secondValue());
                    case UPDATE_CAR -> COMMAND_UPDATE_CAR.edit(pair.secondValue());
                    case DELETE_CAR -> COMMAND_DELETE_CAR.delete(pair.secondValue());
                    case USERS -> COMMAND_USERS.users(pair.secondValue());
                    case EDIT_USER -> COMMAND_EDIT_USER.edit(pair.secondValue());
                    case BUY_CAR -> COMMAND_BUY_CAR.buy(user,pair.secondValue());
                    case ORDERS -> COMMAND_ORDERS.orders(pair.secondValue());
                    case CANCEL_ORDER -> COMMAND_CANCEL_ORDER.cancel(pair.secondValue());
                    case UPDATE_STATUS -> COMMAND_UPDATE_STATUS.update(pair.secondValue());
                    case LOGS -> COMMAND_LOGS.logs(pair.secondValue());
                    case SAVE_LOGS -> COMMAND_SAVE_LOGS.logs(pair.secondValue());
                    default -> OUTPUT.println("Данная комманда временно не поддерживается");
                }
            }
        }
    }
}
