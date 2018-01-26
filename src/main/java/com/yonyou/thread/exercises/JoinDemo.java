package com.yonyou.thread.exercises;

import sun.java2d.SurfaceDataProxy;

import java.util.concurrent.CountDownLatch;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/12 11:22
 */

public class JoinDemo {
    private static CountDownLatch countDownLatch=new CountDownLatch(3);
    public static void main(String[] args) throws InterruptedException {
        Thread A=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("A begin");
                    Thread.sleep(1000);
                    System.out.println("A end");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread B=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    A.join();
                    System.out.println("B begin");
                    Thread.sleep(1000);
                    System.out.println("B end");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread C=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    B.join();
                    System.out.println("C begin");
                    Thread.sleep(1000);
                    System.out.println("C end");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        A.start();
        B.start();
        C.start();
        countDownLatch.await();
    }
}
