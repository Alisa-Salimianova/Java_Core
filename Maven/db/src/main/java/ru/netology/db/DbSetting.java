package ru.netology.db;

public class DbSetting {
    private String name;
    private String password;

    public DbSetting(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // getterы
    public String getName() { return name; }
    public String getPassword() { return password; }
}
