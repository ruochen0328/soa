package com.yonyou.thread.exercises;

import org.springframework.context.annotation.Bean;

/**
 * @author yangwshh@yonyou.com
 * @date 2017/12/12 14:50
 */
//写两个线程，一个线程打印1~52，另一个线程打印A~Z，打印顺序是12A34B...5152Z；
public class Demo1 {
    static class Behaver{
        private volatile boolean flag=true;
        private int count=1;
        public synchronized void printNum(){
            while(!flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(count*2-1);
            System.out.print(count*2);
            flag=false;
            this.notify();
        }
        public synchronized void printChar(){
            while(flag){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print((char)(count+'A'-1));
            count++;
            flag = true;
            this.notify();
        }
    }

    public static void main(String[] args) {
        Behaver behaver=new Behaver();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    behaver.printNum();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 26; i++) {
                    behaver.printChar();
                }
            }
        }).start();
    }
}
