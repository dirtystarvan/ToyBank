package ru.sbt.toybank;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FrontEnd {
    private BlockingQueue<Application> queue;

    /*
    * ������ �� ��������� ������� �� ���� ��������� ��������� ��� ��� ������� ���������� �� �������, ��� � �� ������
    * ������ ��� ��������� ���������� ���������� ���������, �� ��� ������, ����� � ������� ���������� ������ �����������
    * ������ ���� ��� ������ � �������� ������� ������ � ������ � �������, � � ������ ���� ��������� �� ��� ��������
    */

    FrontEnd() {
        queue = new LinkedBlockingQueue<>(2);
    }

    public Application getApp() {
        synchronized (queue) {
            return queue.poll();
        }
    }

    public void setApp(Application item) {
        try {
            queue.put(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
