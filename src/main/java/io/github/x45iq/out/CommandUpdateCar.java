package io.github.x45iq.out;

import io.github.x45iq.data.MarketDatabase;
import io.github.x45iq.logger.Logger;
import io.github.x45iq.models.Car;
import io.github.x45iq.models.Command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Класс команды update_car
 */
public class CommandUpdateCar {
    private final Output output;
    private final MarketDatabase marketDatabase;
    private static final Pattern FIELD_REGEX = Pattern.compile("--field-(brand|model|year|price) (\\S+)");
    private static final Logger LOGGER = Logger.getInstance();

    public CommandUpdateCar(Output output, MarketDatabase marketDatabase) {
        this.output = output;
        this.marketDatabase = marketDatabase;
    }

    /**
     * Обновляет информацию об авто
     * @param command
     */
    public void edit(String command){
        Matcher matcher =  Command.UPDATE_CAR.getPattern().matcher(command);
        matcher.find();
        long id = Long.parseLong(matcher.group(1));
        matcher = FIELD_REGEX.matcher(command);
        Car car = marketDatabase.read(id);
        if(car==null){
            output.println("Машины с таким id не существует");
            return;
        }
        String brand = car.brand(),model = car.model();
        int year = car.year();
        long price = car.price();
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
        if(year <= 0 || price <0){
            output.println("Значения не соответствуют действительности");
        }else{
            marketDatabase.update(id,new Car(brand,model,year,price));
            output.println("Машина успешно отредактирована");
            LOGGER.log("Данные об автомобиле изменены");
        }
    }
}
