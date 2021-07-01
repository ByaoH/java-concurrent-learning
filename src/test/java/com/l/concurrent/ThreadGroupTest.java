package com.l.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ThreadGroupTest {
    @Test
    public void test() throws Exception {
        new Thread(() -> {
            System.out.println("当前线程组" + Thread.currentThread().getThreadGroup().getName());
            System.out.println("当前线程名" + Thread.currentThread().getName());
        }, "runnableTest").start();
        Thread.sleep(100);
        System.out.println(Thread.currentThread().getThreadGroup().getName());
        System.out.println(Thread.currentThread().getName());
    }

    @Test
    public void priorityTest() throws Exception {
        List<Thread> list = new ArrayList<>();
        IntStream.range(1, 10).forEach(i -> {
            Thread thread = new Thread(() -> {
                System.out.println("线程名:" + Thread.currentThread().getName() + ",优先级" + Thread.currentThread().getPriority());
            });
            thread.setPriority(i);
            list.add(thread);
        });
        list.forEach(Thread::start);
    }

    /**
     * 如果某个线程优先级大于线程所在线程组的最大优先级，那么该线程的优先级将会失效，取而代之的是线程组的最大优先级。
     */
    @Test
    public void groupPriorityTest() {
        ThreadGroup threadGroup = new ThreadGroup("groupPriorityTest");
        threadGroup.setMaxPriority(7);
        Thread thread = new Thread(threadGroup, () -> {
            System.out.println("我的优先级 : " + Thread.currentThread().getPriority());
            System.out.println("线程组的最大优先级 : " + Thread.currentThread().getThreadGroup().getMaxPriority());
        });
        thread.setPriority(9);
        thread.start();
    }

    /**
     * 线程组统一异常处理
     */
    @Test
    public void groupPriorityExceptionTest() {
        ThreadGroup groupPriorityExceptionTest = new ThreadGroup("groupPriorityExceptionTest") {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + " " + e.getMessage());
            }
        };

        new Thread(groupPriorityExceptionTest, () -> {
            throw new RuntimeException("测试抛出异常");
        }).start();
    }
}
