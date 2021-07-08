package ru.sbt.toybank;

//сущность заявки

public class Application {
    String clientName;
    long value;
    AppType type;

    public Application(String clientName, long value, AppType type) {
        this.clientName = clientName;
        this.value = value;
        this.type = type;
    }
}
