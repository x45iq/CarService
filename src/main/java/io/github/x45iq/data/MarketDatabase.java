package io.github.x45iq.data;

import io.github.x45iq.models.Car;

import java.util.HashMap;
import java.util.Map;

/**
 * Представление хранилища доступных машин
 */
public final class MarketDatabase {
    private long currentId = 1;
    private final Map<Long,Car> lots = new HashMap<>();
    {
        create(new Car("BMW","X5",2020,14_500_000));
        create(new Car("Porsche","Cayenne",2024,15_800_000));
        create(new Car("BMW","X6",2021,13_790_000));
        create(new Car("Volkswagen","Passat",2010,1_600_000));
        create(new Car("Toyota","Camry",2019,2_990_000));
        create(new Car("Lada","Granta",2019,600_000));
    }
    public long create(Car car){
        lots.put(currentId++,car);
        return currentId-1;
    }
    public Car read(long id){
        return lots.get(id);
    }
    public Map<Long,Car> readAll(){
        return new HashMap<>(lots);
    }
    public void update(long id,Car car){
        lots.replace(id,car);
    }
    public boolean delete(long id){
        return lots.remove(id)!=null;
    }
}
