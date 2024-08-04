package io.github.x45iq.data;

import io.github.x45iq.models.Role;
import io.github.x45iq.models.User;

import java.util.HashMap;
import java.util.Map;
/**
 * Представление хранилища пользователей
 */
public final class UserDatabase {
    private final Map<String,User> USER_LIST = new HashMap<>();
    {
        create("admin",new User(Role.ADMIN,"admin".hashCode()));
        create("client",new User(Role.CLIENT,"client".hashCode()));
        create("manager",new User(Role.MANAGER,"manager".hashCode()));
        create("random",new User(Role.MANAGER,"5433221".hashCode()));
    }
    public void create(String id,User user){
        USER_LIST.put(id,user);
    }
    public User read(String id){
        return USER_LIST.get(id);
    }
    public Map<String,User> readAll(){
        return new HashMap<>(USER_LIST);
    }
    public void update(String id,User user){
        USER_LIST.replace(id,user);
    }

    public void delete(String id){
        USER_LIST.remove(id);
    }
}
