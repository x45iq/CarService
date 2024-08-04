package io.github.x45iq.models;

import java.util.Arrays;
import java.util.Map;

/**
 * Класс представления роли пользователя
 */
public enum Role {
    ADMIN(15,"admin"),
    MANAGER(10,"manager"),
    CLIENT(5,"client");
    private final int priority;
    private final String strValue;
    Role(int priority,String strValue){
        this.priority = priority;
        this.strValue = strValue;
    }
    public static Role fromString(String str){
        return Arrays.stream(Role.values()).filter(role->role.toString().equals(str)).findFirst().orElse(null);
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return strValue;
    }
}
