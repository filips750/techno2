package org.example;

class ComputationThread implements Runnable {
    private SharedResource taskResource;
    private ResultCollector resultCollector;

    public ComputationThread(SharedResource taskResource, ResultCollector resultCollector) {
        this.taskResource = taskResource;
        this.resultCollector = resultCollector;
    }

    @Override
    public synchronized void run () {
        try {
            while (true) {
                int task;
                task = taskResource.getTask();
                boolean result = performComputation(task);
                resultCollector.addResult(result);
            }
        } catch (InterruptedException e) {
            // Thread interrupted, exit gracefully
        }
    }

    private boolean performComputation(int task) {
        if (task%2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(task); i = i + 2) {
            if (task % i == 0) {
                return false; // liczba nie jest pierwsza
            }
        }
        return true; // liczba jest pierwsza
    }
}