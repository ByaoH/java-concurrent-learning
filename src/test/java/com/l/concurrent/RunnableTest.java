package com.l.concurrent;

import org.junit.Test;

public class RunnableTest {
    @Test
    public void test() {
        new Thread(new Task()).start();
        new Thread(() -> System.out.println("匿名类")).start();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task.run");
        }
    }
}
