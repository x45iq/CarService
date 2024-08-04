package io.github.x45iq.data;

import io.github.x45iq.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MarketDatabaseTest {
    private MarketDatabase marketDatabase = null;
    @BeforeEach
    public void before(){
        marketDatabase = new MarketDatabase();
    }

    @Test
    void createAndRead() {
        Car car = Mockito.mock();
        long id = marketDatabase.create(car);
        assertThat(marketDatabase.read(id)).isEqualTo(car);
    }

    @Test
    void updateAndDelete() {
        Car car = Mockito.mock();
        Car car2 = Mockito.mock();
        long id = marketDatabase.create(car);
        assertThat(marketDatabase.read(id)).isEqualTo(car);
        marketDatabase.update(id,car2);
        assertThat(marketDatabase.read(id)).isEqualTo(car2);
        marketDatabase.delete(id);
        assertThat(marketDatabase.read(id)).isNull();
    }

}