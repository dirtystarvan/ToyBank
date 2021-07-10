package ru.sbt.toybank;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FrontEnd {
    private BlockingQueue<Application> queue;

    /*
    * –абота со св€занным списком из двух элементов одинакова как дл€ очереди основанной на массиве, так и на списке
    * ќднако при возможном расширении количества элементов, на мой взгл€д, выбор в сторону св€занного списка оптимальнее
    * засчет того что работа с очередью ведетс€ только с концом и началом, а у списка есть указатели на эти элементы
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
