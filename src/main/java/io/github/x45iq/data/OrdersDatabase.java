package io.github.x45iq.data;

import io.github.x45iq.models.Car;
import io.github.x45iq.models.Order;

import java.util.HashMap;
import java.util.Map;
/**
 * Представление хранилища заказов
 */
public class OrdersDatabase {
    private long currentId = 1;
    private final Map<Long, Order> orders = new HashMap<>();
    {

    }
    public long create(Order order){
        orders.put(currentId++,order);
        return currentId-1;
    }
    public Order read(long id){
        return orders.get(id);
    }
    public Map<Long,Order> readAll(){
        return new HashMap<>(orders);
    }
    public void update(long id,Order order){
        orders.replace(id,order);
    }
    public boolean delete(long id){
        return orders.remove(id)!=null;
    }
}
