package ru.sbt.toybank;

import java.util.Random;

//есть сущность клиента
//является отдельным потоком. Миниум 4 клиента.
//клиент должен иметь возможность подать только одну заявку увеличить или уменьшить баланс
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
        System.out.printf("%s: Заявка передана в банк! %s %d\n", this.getName(), sign ? "Положить" : "Снять", money);
    }

    @Override
    public void run() {
        createApplication();
    }
}
