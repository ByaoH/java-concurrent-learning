package com.l.concurrent;

import org.junit.Test;

/**
 * ThreadStatus
 *
 * @author luliangyu
 * @date 2021-07-01 11:34
 */
public class ThreadState {
    /**
     * 线程被创建就相当于进入了NEW状态
     */
    @Test
    public void newTest() {
        Thread thread = new Thread(() -> {
            System.out.println("ThreadState.newTest");
        });
        System.out.println(thread.getState());
        thread.start();
        try {
            thread.start();
        } catch (Exception e) {
//            重复调用会出现异常 这一点可以从源码中threadStatus 属性 看出
            e.printStackTrace();
            System.err.println("测试重复调用异常");
        }
    }
}
