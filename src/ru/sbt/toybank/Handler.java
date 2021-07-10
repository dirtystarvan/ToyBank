package ru.sbt.toybank;

public class Handler implements Runnable {
    private String name;
    private BackEnd back;
    private FrontEnd front;

    public Handler(String name, BackEnd back, FrontEnd front) {
        this.name = name;
        this.back = back;
        this.front = front;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            Application currentApp = front.getApp();

            if (currentApp == null)
                continue;

            switch (currentApp.type) {
                case ADD:
                    System.out.printf("%s: получена заявка на ввод средств по клиенту %s\n",
                            name, currentApp.clientName);
                    back.addMoney(currentApp.value);
                    break;
                case WITHDRAW:
                    System.out.printf("%s: получена заявка на вывод средств по клиенту %s\n",
                            name, currentApp.clientName);
                    back.withdrawMoney(currentApp.value);
                    break;
                default:
                    System.out.printf("%s: получена заявка неизветсного типа по клиенту %s\n",
                            name, currentApp.clientName);
            }
        }
    }
}
