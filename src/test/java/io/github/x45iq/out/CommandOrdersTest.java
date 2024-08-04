package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandOrdersTest {

    @Test
    void orders() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CommandOrders commandOrders = new CommandOrders(new Output(new PrintStream(byteArrayOutputStream)),new OrdersDatabase());
        commandOrders.orders("orders");
        assertThat(byteArrayOutputStream.toString()).isNotEqualTo(null);
    }
}