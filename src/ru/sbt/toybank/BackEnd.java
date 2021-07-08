package ru.sbt.toybank;

public class BackEnd {
    private long account;

    public synchronized void addMoney(long value) {
        account += value;
        System.out.printf("Back: Заявка выполнена! Добавлено %d. Текущее состояние счета: %d\n", value, account);
    }

    public synchronized void withdrawMoney(long value) {
        if (value > account)
            System.out.println("Back: Заявка не выполнена! Недостаточно средств в банке!");
        else {
            account -= value;
            System.out.printf("Back: Заявка выполнена! Выдано клиенту %d. Текущее состояние счета: %d\n", value, account);
        }
    }
}
