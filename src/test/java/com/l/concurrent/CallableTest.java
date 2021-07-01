package com.l.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

public class CallableTest {
    @Test
    public void futureTest() throws Exception {
        ExecutorService poll = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> f1 = poll.submit(task);
        Future<Integer> f2 = poll.submit(task);
        System.out.println("f1 = " + f1.get());
        System.out.println("f2 = " + f2.get());
    }

    /**
     * <h1>FutureTask<h1/>
     * 区别
     * <ul>
     *     <li>1.submit没有返回值 实际是使用submit(Runnable task)方法</li>
     *     <li>2.FutureTask能够在高并发环境下确保任务只执行一次</li>
     * </ul>
     *
     * @throws Exception
     */
    @Test
    public void futureTaskTest() throws Exception {
        ExecutorService poll = Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask = new FutureTask<>(new Task());
        Future<?> submit = poll.submit(futureTask);
        System.out.println("submit.get() = " + submit.get());
        System.out.println("futureTask = " + futureTask.get());
    }

    private class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
//            模拟计算时间
            System.out.println("Thread.currentThread() = " + Thread.currentThread());
            Thread.sleep(1000);
            return (int) (Math.random() * 10000);
        }
    }
}
