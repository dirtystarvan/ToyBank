package ru.sbt.toybank;

import java.util.LinkedList;
import java.util.Queue;

public class FrontEnd {
    private Queue<Application> queue;

    FrontEnd() {
        queue = new LinkedList<>();
    }

    public Application getApp() {
        synchronized (queue) {
            queue.notifyAll();
            return queue.poll();
        }
    }

    public void setApp(Application item) {
        synchronized (queue) {
            while (queue.size() > 2) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            queue.add(item);
        }
    }
}
