package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Command;

import java.util.regex.Matcher;

/**
 * Класс команды cancel_order
 */
public class CommandCancelOrder {
    private final Output output;
    private final OrdersDatabase ordersDatabase;
    private static final Logger LOGGER = Logger.getInstance();

    public CommandCancelOrder(Output output, OrdersDatabase ordersDatabase) {
        this.output = output;
        this.ordersDatabase = ordersDatabase;
    }

    /**
     * Удаляет выбранный заказ
     * @param command
     */
    public void cancel(String command){
        Matcher matcher =  Command.CANCEL_ORDER.getPattern().matcher(command);
        matcher.find();
        long id = Long.parseLong(matcher.group(1));
        if(ordersDatabase.delete(id)){
            output.println("Заказ успешно удалён");
            LOGGER.log("Удален заказ, id заказа = %s".formatted(id));
        }else{
            output.println("Неверные данные");
        }
    }
}
