package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.data.OrdersDatabase;
import io.github.x45iq.data.UserDatabase;
import io.github.x45iq.models.Car;
import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandAddCarTest {

    @Test
    void create() {
        MarketDatabase marketDatabase = new MarketDatabase();
        CommandAddCar commandAddCar = new CommandAddCar(Mockito.mock(),marketDatabase);
        commandAddCar.create("add_car --field-brand BMW --field-model red --field-year 2000 --field-price 3000000");
        assertThat(marketDatabase.readAll().entrySet().stream().map(Map.Entry::getValue).filter(car -> car.equals(new Car("BMW","red",2000,3000000))).findFirst().orElse(null)).isNotEqualTo(null);
    }
}