package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.models.Car;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CommandDeleteCarTest {

    @Test
    void delete() {
        MarketDatabase marketDatabase = new MarketDatabase();
        CommandDeleteCar commandDeleteCar = new CommandDeleteCar(Mockito.mock(),marketDatabase);
        long id = marketDatabase.create(new Car("ferrari","1234",43321,1231323));
        commandDeleteCar.delete("delete_car %s".formatted(id));
        assertThat(marketDatabase.read(id)).isEqualTo(null);
    }
}