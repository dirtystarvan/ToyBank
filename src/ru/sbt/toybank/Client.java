package ru.sbt.toybank;

import java.util.Random;

public class Client implements Runnable {
    private String name;
    private FrontEnd front;

    public Client(FrontEnd front, String name) {
        this.name = name;
        this.front = front;
    }

    public void createApplication() {
        Random random = new Random();
        long money = (long)(Math.random() * 1000000);
        boolean sign = random.nextBoolean();
        front.setApp(new Application(name, money, sign ? AppType.ADD : AppType.WITHDRAW));
        System.out.printf("%s: Заявка передана в банк! %s %d\n", Thread.currentThread().getName(), sign ? "Положить" : "Снять", money);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        createApplication();
    }
}
