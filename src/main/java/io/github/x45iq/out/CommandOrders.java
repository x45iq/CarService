package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Command;
import io.github.x45iq.models.Order;
import io.github.x45iq.models.User;

import java.util.Map;
import java.util.regex.Matcher;

/**
 * Класс команды orders
 */
public class CommandOrders {
    private final Output output;
    private final OrdersDatabase ordersDatabase;

    public CommandOrders(Output output, OrdersDatabase ordersDatabase) {
        this.output = output;
        this.ordersDatabase = ordersDatabase;
    }

    /**
     * Выводит все заказы на покупку
     * @param command
     */
    public void orders(String command){
        ordersDatabase.readAll().forEach((key, value) -> output.printf("id=%s, %s\n", key, value));
    }
}
