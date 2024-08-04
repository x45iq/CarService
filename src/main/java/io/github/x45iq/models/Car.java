package io.github.x45iq.models;

import java.time.LocalDate;

/**
 * Класс представления авто
 * @param brand
 * @param model
 * @param year
 * @param price
 */
public record Car(String brand,String model,int year,long price) {
    public String toStringForPrint() {
        return "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price;
    }
}
