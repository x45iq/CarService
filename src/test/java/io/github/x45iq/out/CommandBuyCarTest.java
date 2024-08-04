package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Order;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandBuyCarTest {

    @Test
    void buy() {
        UserDatabase userDatabase = new UserDatabase();
        OrdersDatabase ordersDatabase = new OrdersDatabase();
        CommandBuyCar commandBuyCar = new CommandBuyCar(Mockito.mock(),userDatabase,ordersDatabase);
        commandBuyCar.buy(new User(Role.ADMIN,"admin".hashCode()),"buy_car 1");
        assertThat(ordersDatabase.readAll().entrySet().stream().map(Map.Entry::getValue).filter(order -> order.equals(new Order("admin",1))).findFirst().orElse(null)).isNotEqualTo(null);
    }
}