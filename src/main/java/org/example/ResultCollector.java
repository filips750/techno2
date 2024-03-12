package org.example;

import java.util.LinkedList;

class ResultCollector {
    private LinkedList<Object> results = new LinkedList<>();

    public synchronized void addResult(boolean result) {
        results.add(result);
    }

    public synchronized void displayResults() {
        System.out.println("Results:");
        for (Object result : results) {
            System.out.println(result);
        }
    }
}