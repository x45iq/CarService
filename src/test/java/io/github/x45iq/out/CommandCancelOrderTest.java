package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Order;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandCancelOrderTest {

    @Test
    void cancel() {
        OrdersDatabase ordersDatabase = new OrdersDatabase();
        CommandCancelOrder commandCancelOrder = new CommandCancelOrder(Mockito.mock(),ordersDatabase);
        long id = ordersDatabase.create(new Order("1234",1234));
        commandCancelOrder.cancel("cancel_order %s".formatted(id));
        assertThat(ordersDatabase.read(id)).isEqualTo(null);
    }
}