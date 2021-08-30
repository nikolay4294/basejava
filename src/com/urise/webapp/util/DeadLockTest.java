package com.urise.webapp.util;

public class DeadLockTest {
    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        executeDeadLock(Lock1, Lock2);
    }

    private static void executeDeadLock(Object Lock1, Object Lock2) {
        Thread thread1 = new Thread(() -> {
            synchronized (Lock1) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lock1 is holding");
            }
            synchronized (Lock2) {
                System.out.println("Lock2 is holding");
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (Lock2) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lock2 is holding");
            }
            synchronized (Lock1) {
                System.out.println("Lock1 is holding");
            }
        });
        thread1.start();
        thread2.start();
    }
}