package ru.sbt.toybank;

//���� �������� ������, �������� ��� ������ (�������), �����, ��� ��������
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
