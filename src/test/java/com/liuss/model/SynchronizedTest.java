package com.liuss.model;

import org.junit.Test;

import java.util.UUID;

public class SynchronizedTest {
    public synchronized void methodA() throws InterruptedException {
        System.out.println("methodA");
        Thread.sleep(5000);
    }
    public void methodB()
    {
        synchronized (this){
            System.out.println("methodB");
        }
    }
    public void methodC()
    {
        String str="11";
        synchronized (str){
            System.out.println("methodC");
        }
    }
    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            try {
                methodA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> methodB()).start();
        new Thread(()->{
            methodC();
        }).start();
        Thread.sleep(10000);
    }
    @Test
    public void Test1()
    {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
