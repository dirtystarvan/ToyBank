package ru.sbt.toybank;

import java.util.concurrent.atomic.AtomicLong;

public class BackEnd {
    private AtomicLong account = new AtomicLong();

    public void warmup(long value) {
        account.addAndGet(value);
    }

    public void addMoney(long value) {
        //account += value;
        account.addAndGet(value);
        System.out.printf("Back: ������ ���������! ��������� %d. ������� ��������� �����: %d\n",
                value, account.get());
    }

    public void withdrawMoney(long value) {
        if (value > account.get())
            System.out.println("Back: ������ �� ���������! ������������ ������� � �����!");
        else {
            //account -= value;
            account.addAndGet(value * (-1));
            System.out.printf("Back: ������ ���������! ������ ������� %d. ������� ��������� �����: %d\n",
                    value, account.get());
        }
    }
}
