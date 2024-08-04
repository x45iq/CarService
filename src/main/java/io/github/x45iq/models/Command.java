package io.github.x45iq.models;

import java.util.regex.Pattern;

/**
 * Класс представления команды
 */
public enum Command {
    HELP("help",Pattern.compile("help (\\S++)"),Role.CLIENT),
    EXIT("exit",Pattern.compile("exit"),Role.CLIENT),
    CAR_LIST("car_list",Pattern.compile("car_list(.*)"),Role.CLIENT),
    ADD_CAR("add_car",Pattern.compile("add_car(.*)"),Role.MANAGER),
    UPDATE_CAR("update_car",Pattern.compile("update_car (\\d++)(.*)"),Role.MANAGER),
    DELETE_CAR("delete_car",Pattern.compile("delete_car (\\d++)"),Role.MANAGER),
    BUY_CAR("buy_car",Pattern.compile("buy_car (\\d++)"),Role.CLIENT),
    USERS("users",Pattern.compile("users(.*)"),Role.ADMIN),
    EDIT_USER("edit_user",Pattern.compile("edit_user (\\S++)(.*)"),Role.ADMIN),
    ORDERS("orders",Pattern.compile("orders"),Role.MANAGER),
    CANCEL_ORDER("cancel_order",Pattern.compile("cancel_order (\\d++)"),Role.MANAGER),
    UPDATE_STATUS("update_status",Pattern.compile("update_status (\\d++)"),Role.MANAGER),
    LOGS("logs",Pattern.compile("logs(.*)"),Role.ADMIN),
    SAVE_LOGS("save_logs",Pattern.compile("save_logs (\\S++)"),Role.ADMIN);


    private final String stringValue;
    private final Pattern pattern;
    private final Role minimalRole;

    Command(String stringValue, Pattern pattern, Role minimalRole) {
        this.stringValue = stringValue;
        this.pattern = pattern;
        this.minimalRole = minimalRole;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public Role getMinimalRole() {
        return minimalRole;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}
