package io.github.x45iq.models;

import java.util.Objects;

/**
 * Класс представление пользователя
 */
public class User {
    private final Role role;
    private final int hashPassword;
    private String name = "";
    private String contact = "";
    private int transactionsCount = 0;

    public User(Role role, int hashPassword) {
        this.role = role;
        this.hashPassword = hashPassword;
    }
    public boolean comparePasswords(int hashPassword){
        return hashPassword == this.hashPassword;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public int getTransactionsCount() {
        return transactionsCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String toStringForPrint() {
        return "role=" + role +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", transactionsCount=" + transactionsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return hashPassword == user.hashPassword && transactionsCount == user.transactionsCount && role == user.role && Objects.equals(name, user.name) && Objects.equals(contact, user.contact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, hashPassword, name, contact, transactionsCount);
    }
}
