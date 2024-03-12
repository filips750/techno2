package org.example;

import java.util.LinkedList;
import java.util.Queue;

class SharedResource  {
    private Queue<Integer> queue = new LinkedList<>();

    public synchronized void addTask(int task) {
        queue.offer(task);
        notifyAll();
    }

    public synchronized int getTask() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        int task = queue.poll();
        notifyAll();
        return task;
    }


}
