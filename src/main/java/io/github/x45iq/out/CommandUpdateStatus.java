package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.Order;

import java.util.regex.Matcher;
/**
 * Класс команды update_status
 */
public class CommandUpdateStatus {
    private final Output output;
    private final OrdersDatabase ordersDatabase;
    private final MarketDatabase marketDatabase;
    private static final Logger LOGGER = Logger.getInstance();

    public CommandUpdateStatus(Output output,OrdersDatabase ordersDatabase, MarketDatabase marketDatabase) {
        this.ordersDatabase = ordersDatabase;
        this.marketDatabase = marketDatabase;
        this.output = output;
    }

    /**
     * Переводит статус заказа в продано
     * @param command
     */
    public void update(String command){
        Matcher matcher =  Command.UPDATE_STATUS.getPattern().matcher(command);
        matcher.find();
        long id = Long.parseLong(matcher.group(1));
        var order = ordersDatabase.read(id);
        if(order==null){
            output.println("Неверные данные");
            return;
        }
        order.updateStatus();
        ordersDatabase.update(id,order);
        marketDatabase.delete(order.getCarId());
        ordersDatabase.readAll().forEach((key,value)->{
            if(value.getCarId()==id && !value.equals(order)){
                ordersDatabase.delete(key);
            }
        });
        output.println("Машина успешно продана");
        LOGGER.log("Машина продана, id заказа = %s".formatted(id));

    }
}
