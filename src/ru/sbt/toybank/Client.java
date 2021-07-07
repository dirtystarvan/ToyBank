package ru.sbt.toybank;

import java.util.Random;

//���� �������� �������
//�������� ��������� �������. ������ 4 �������.
//������ ������ ����� ����������� ������ ������ ���� ������ ��������� ��� ��������� ������
public class Client extends Thread {
    FrontEnd front;

    public Client(FrontEnd front, String name) {
        super(name);
        this.front = front;
    }

    public void createApplication() {
        Random random = new Random();
        long money = (long)(Math.random() * 1000000);
        boolean sign = random.nextBoolean();
        front.setApp(new Application(this.getName(), money, sign ? AppType.ADD : AppType.WITHDRAW));
        System.out.printf("%s: ������ �������� � ����! %s %d\n", this.getName(), sign ? "��������" : "�����", money);
    }

    @Override
    public void run() {
        createApplication();
    }
}
