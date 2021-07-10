package ru.sbt.toybank;

import java.util.LinkedList;
import java.util.Queue;
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
//        synchronized (queue) {
//            queue.notifyAll();
//            return queue.poll();
//        }
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setApp(Application item) {
//        synchronized (queue) {
//            while (queue.size() > 2) {
//                try {
//                    queue.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            queue.add(item);
//        }

        try {
            queue.put(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
