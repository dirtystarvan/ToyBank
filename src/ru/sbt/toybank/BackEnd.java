package ru.sbt.toybank;

public class BackEnd {
    private long account;

    public synchronized void addMoney(long value) {
        account += value;
        System.out.printf("Back: ������ ���������! ��������� %d. ������� ��������� �����: %d\n", value, account);
    }

    public synchronized void withdrawMoney(long value) {
        if (value > account)
            System.out.println("Back: ������ �� ���������! ������������ ������� � �����!");
        else {
            account -= value;
            System.out.printf("Back: ������ ���������! ������ ������� %d. ������� ��������� �����: %d\n", value, account);
        }
    }
}
