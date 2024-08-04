package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Car;
import io.github.x45iq.models.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandUpdateCarTest {

    @Test
    void edit() {
        MarketDatabase marketDatabase = new MarketDatabase();
        CommandUpdateCar commandUpdateCar = new CommandUpdateCar(Mockito.mock(),marketDatabase);
        long id = marketDatabase.create(new Car("ferrari","1234",43321,1231323));
        commandUpdateCar.edit("update_car %s --field-brand BMW".formatted(id));
        assertThat(marketDatabase.read(id).brand()).isEqualTo("BMW");
    }
}