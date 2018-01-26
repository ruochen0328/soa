package com.yonyou.thread.exercises;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/14 10:42
 */

public class LockCondtionDemo {
    private static class Mywork{
        private volatile boolean flag=true;//是否等待
        private Lock lock=new ReentrantLock();
        private Condition condition=lock.newCondition();
        public void sub(){
            try{
                lock.lock();
                while (!flag){
                    try {
                        condition.await();//释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("sub run ----------------");
                flag=false;
                condition.signal();
            }finally {
                lock.unlock();//这里一定要释放锁
            }
        }
        public void main(){
            try{
                lock.lock();
                while(flag){
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("main run");
                flag=true;
                condition.signal();
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        Mywork mywork=new Mywork();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    mywork.sub();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    mywork.main();
                }
            }
        }).start();
    }
}
