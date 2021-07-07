package ru.sbt.toybank;

//есть сущность бэк системы - выполняет операцию со счетом банка увеличить или уменьшить, если возможно
//содержит поле общего денежного баланса с синхронизированным доступом
// (синхронизированне методы по объекту для увеличения и уменьшения баланса)
public class BackEnd {
    private long account;

    public synchronized void addMoney(long value) {
        account += value;
        System.out.printf("Заявка выполнена! Добавлено %d. Текущее состояние счета: %d\n", value, account);
    }

    public synchronized void withdrawMoney(long value) {
        if (value > account)
            System.out.println("Заявка не выполнена! Недостаточно средств в банке!");
        else {
            account -= value;
            System.out.printf("Заявка выполнена! Выдано клиенту %d. Текущее состояние счета: %d\n", value, account);
        }
    }
}
