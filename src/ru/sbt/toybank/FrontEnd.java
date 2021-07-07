package ru.sbt.toybank;

import java.util.LinkedList;
import java.util.Queue;

//���� �������� ����������� �������
//��� ������� � �������� ���������� ������ ��� �������� � �������� ��������� ��� ������������
//���������� ����� wait � notifyAll. �� ����� ��������� ����� ���� ������
public class FrontEnd {
    private Queue<Application> queue;

    FrontEnd() {
        queue = new LinkedList<>();
    }

    public synchronized Application getApp() {
        if (queue.isEmpty()) {
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            return null;
        } else {
            notifyAll();
        }

        return queue.poll();
        //return queue.isEmpty() ? null : queue.poll();
    }

    public synchronized void setApp(Application item) {
        if (queue.size() < 2) {
            queue.offer(item);
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //return queue.size() > 2 && queue.offer(item);
    }
}
