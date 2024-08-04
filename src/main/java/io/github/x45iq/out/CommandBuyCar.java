package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.Order;
import io.github.x45iq.models.User;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * Класс команды buy_car
 */
public class CommandBuyCar {
    private final Output output;
    private final OrdersDatabase ordersDatabase;
    private final UserDatabase userDatabase;
    private static final Logger LOGGER = Logger.getInstance();

    public CommandBuyCar(Output output, UserDatabase userDatabase, OrdersDatabase ordersDatabase) {
        this.output = output;
        this.ordersDatabase = ordersDatabase;
        this.userDatabase = userDatabase;
    }

    /**
     * Отправляет заявку на покупку машины
     * @param user
     * @param command
     */
    public void buy(User user,String command){
        Matcher matcher =  Command.BUY_CAR.getPattern().matcher(command);
        matcher.find();
        long id = Long.parseLong(matcher.group(1));
        String userID = userDatabase.readAll().entrySet().stream().filter(stringUserEntry -> stringUserEntry.getValue().equals(user)).findFirst().map(Map.Entry::getKey).orElse(null);
        if(id<=0 || userID==null){
            output.println("Введены неверные данные");
            return;
        }
        Order order = new Order(userID,id);
        ordersDatabase.readAll().values().stream().filter(e->e.equals(order)).findFirst().ifPresentOrElse(order1 -> {
            output.println("Нельяз оставлять несколько заявок на один автомобиль");
        },()->{
            ordersDatabase.create(new Order(userID,id));
            output.println("Заявка на автомобиль успешно оставлена");
            LOGGER.log("Оставлена заявка на автомобиль, carId=%s".formatted(id));
        });
    }
}
