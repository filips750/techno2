package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Main {
    public void main(String[] args) throws InterruptedException {
        int numThreads = Integer.parseInt(args[0]);
        SharedResource taskResource = new SharedResource();
        ResultCollector resultCollector = new ResultCollector();
        List threads = new ArrayList<Thread>();
        // Create and start computation threads
        for (int i = 0; i < numThreads; i++) {
            ComputationThread task = new ComputationThread(taskResource, resultCollector);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }

        long start = System.currentTimeMillis();

        // User input handling for adding tasks and closing the application
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a task (or 'exit' to close the application): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                Thread.sleep(10);
                // Closing the application
                for (Thread thread : Thread.getAllStackTraces().keySet()) {
                    thread.interrupt();
                }
                resultCollector.displayResults();
                System.exit(0);
            } else {
                // Adding task to the shared resource
                taskResource.addTask(Integer.parseInt(input));
            }
        }

    }
}