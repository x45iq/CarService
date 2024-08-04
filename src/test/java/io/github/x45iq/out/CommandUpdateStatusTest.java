package io.github.x45iq.out;

import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.models.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandUpdateStatusTest {

    @Test
    void update() {
        OrdersDatabase ordersDatabase = new OrdersDatabase();
        CommandUpdateStatus commandUpdateStatus = new CommandUpdateStatus(Mockito.mock(),ordersDatabase,Mockito.mock());
        long id = ordersDatabase.create(new Order("admin",1));
        commandUpdateStatus.update("update_status %s".formatted(id));
        assertThat(ordersDatabase.read(id).getStatus()).isEqualTo(Order.Status.SOLD);
    }
}