package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandCarListTest {

    @Test
    void list() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        CommandCarList commandCarList = new CommandCarList(new Output(new PrintStream(byteArrayOutputStream)),new MarketDatabase());
        commandCarList.list("car_list");
        assertThat(byteArrayOutputStream.toString()).isNotEqualTo(null);
    }
}