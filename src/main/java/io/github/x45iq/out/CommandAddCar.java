package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Car;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс команды add_car
 */
public class CommandAddCar {
    private final Output output;
    private final MarketDatabase marketDatabase;
    private static final Logger LOGGER = Logger.getInstance();
    private static final Pattern FIELD_REGEX = Pattern.compile("--field-(brand|model|year|price) (\\S+)");

    public CommandAddCar(Output output, MarketDatabase marketDatabase) {
        this.output = output;
        this.marketDatabase = marketDatabase;
    }

    /**
     * Добавляет машину в базу
     * @param command
     */
    public void create(String command){
        Matcher matcher = FIELD_REGEX.matcher(command);
        String brand = null,model = null;
        int year = -1;
        long price = -1;
        while(matcher.find()){
            String value = matcher.group(2);
            switch (matcher.group(1)){
                case "brand"-> brand = value;
                case "model"-> model = value;
                case "year"-> {
                    try {
                        year = Integer.parseInt(value);
                    } catch (Exception ignored){}
                }
                case "price"-> {
                    try {
                        price = Long.parseLong(value);
                    } catch (Exception ignored){}
                }
                default -> {
                    output.println("Неверный формат запроса");
                    return;
                }
            }
        }
        if(brand==null || model==null || year <= 0 || price <0){
            output.println("Значения не соответствуют действительности или чего-то не хватило");
        }else{
            long id = marketDatabase.create(new Car(brand,model,year,price));
            LOGGER.log("Добавлена машина, id=%s".formatted(id));
            output.println("Машина успешно добавлена");
        }
    }
}
