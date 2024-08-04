package io.github.x45iq.out;

import io.github.x45iq.models.Command;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * Класс команды help
 */
public class CommandHelp {
    private final Output output;
    private static final EnumMap<Command,String> doc = new EnumMap<>(Command.class);
    static {
        doc.put(Command.HELP, """
                Выводит информацию о команде
                Использование: help <command>
                Пример: help car_list
                """);
        doc.put(Command.EXIT, """
                Выходит из акканта пользователя
                Использование: exit
                """);
        doc.put(Command.CAR_LIST, """
                Выводит список автомобилей доступных на площадке
                Использование: car_list
                Дополнительные аргументы: --filter-(id|brand|model|year|price) <id|brand|model|year|price> - фильтрация по параметрам
                Пример: car_list --filter-brand ferrari --filter-year 2015
                """);
        doc.put(Command.ADD_CAR, """
                Добавляет авто на площадку
                Использование: add_car
                Дополнительные аргументы: --field-(brand|model|year|price) <brand|model|year|price> - информация об авто
                Пример: add_car --field-brand BMW --field-model red --field-year 2000 --field-price 3000000
                Примечание: Для добавления авто на плозадку должны присутствовать все его поля
                """);
        doc.put(Command.UPDATE_CAR, """
                Изменяет информацию об авто на плозадке
                Использование: update_car <carId>
                Дополнительные аргументы: --field-(brand|model|year|price) <brand|model|year|price> - информация об авто
                Пример: update_car 3 --field-brand BMW --field-price 15
                """);
        doc.put(Command.DELETE_CAR, """
                Удаляет авто с площадки
                Использование: delete_car <carId>
                Пример: delete_car 2
                """);
        doc.put(Command.BUY_CAR, """
                Оставляет заявку на покупку авто
                Использование: buy_car <carId>
                Пример: buy_car 2
                """);
        doc.put(Command.USERS, """
                Выводит список всех клиентов и менеджеров
                Использование: users
                Дополнительные аргументы:
                --filter-(role|name|contact|transactionsCount) <role|name|contact|transactionsCount> - фильтр
                --ordered-by (transactionsCount) - сортировка
                Пример: users --filter-role manager --ordered-by transactionsCount
                """);
        doc.put(Command.EDIT_USER, """
                Изменяет информацию о пользователе
                Использование: edit_user <userId>
                Дополнительные аргументы: --field-(name|contact) <name|contact>
                Пример: edit_user pups --field-name Oleg --field-contact +775312
                """);
        doc.put(Command.ORDERS, """
                Выводит список заявок на покупку
                Использование: orders
                Пример: orders
                """);
        doc.put(Command.CANCEL_ORDER, """
                Удаляет заявку на покупку
                Использование: cancel_order <orderId>
                Пример: cancel_order 3
                """);
        doc.put(Command.UPDATE_STATUS, """
                Переводит заявку на покупку в статус продано и удаляет все остальные заявки на проданый автомобиль
                Использование: update_status <orderId>
                Пример: update_status 3
                """);
        doc.put(Command.LOGS, """
                Выводит список всех логов
                Использование: logs
                Дополнительные аргументы: --filter-(date|userId) <date|userId> - фильтр
                Пример: logs --filter-date 2011-12-03
                Примечание: дата записывается в формате YYYY-MM-DD
                """);
        doc.put(Command.SAVE_LOGS, """
                Сохраняет все логи в файл
                Использование: save_logs <fileName>
                Пример: save_logs export.txt
                """);
    }

    public CommandHelp(Output output) {
        this.output = output;
    }

    /**
     * Выводит информацию о любой доступной команде
     * @param command
     */
    public void help(String command){
        Matcher matcher = Command.HELP.getPattern().matcher(command);
        if(!matcher.find()){
            output.println("Невереный запрос");
            return;
        }
        String str = matcher.group(1);
        Command com = Arrays.stream(Command.values()).filter(c->c.toString().equals(str)).findFirst().orElse(null);
        if(com==null){
            output.println("Невереный запрос");
            return;
        }
        output.print(doc.get(com));
    }
}
