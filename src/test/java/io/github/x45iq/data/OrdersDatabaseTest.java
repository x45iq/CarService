package io.github.x45iq.data;

import io.github.x45iq.models.Car;
import io.github.x45iq.models.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class OrdersDatabaseTest {

    private OrdersDatabase ordersDatabase = null;
    @BeforeEach
    public void before(){
        ordersDatabase = new OrdersDatabase();
    }

    @Test
    void createAndRead() {
        Order order = Mockito.mock();
        long id = ordersDatabase.create(order);
        assertThat(ordersDatabase.read(id)).isEqualTo(order);
    }

    @Test
    void updateAndDelete() {
        Order order = Mockito.mock();
        Order order2 = Mockito.mock();
        long id = ordersDatabase.create(order);
        assertThat(ordersDatabase.read(id)).isEqualTo(order);
        ordersDatabase.update(id,order2);
        assertThat(ordersDatabase.read(id)).isEqualTo(order2);
        ordersDatabase.delete(id);
        assertThat(ordersDatabase.read(id)).isNull();
    }
}