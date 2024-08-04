package io.github.x45iq.models;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс представления заказа на покупку
 */
public class Order {
    public Order(String userId, long carId) {
        this.userId = userId;
        this.carId = carId;
    }

    public enum Status {
        SENT, SOLD
    }

    private final String userId;
    private final long carId;
    private Status status = Status.SENT;

    public long getCarId() {
        return carId;
    }

    public String getUserId() {
        return userId;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return carId == order.carId && Objects.equals(userId, order.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, carId);
    }

    public void updateStatus() {
        this.status = Status.SOLD;
    }

    @Override
    public String toString() {
        return "userId='" + userId + '\'' +
                ", carId=" + carId +
                ", status=" + status;
    }
}
