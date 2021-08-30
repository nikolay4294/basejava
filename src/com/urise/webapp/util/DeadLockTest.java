package com.urise.webapp.util;

public class DeadLockTest {
    public static final Object Lock1 = new Object();
    public static final Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        DeadLockTestDemo1 d1 = new DeadLockTestDemo1();
        DeadLockTestDemo2 d2 = new DeadLockTestDemo2();

        d1.start();
        d2.start();
    }

    private static class DeadLockTestDemo1 extends Thread {

        public void run() {
            synchronized (Lock1) {
                System.out.println("Lock1 is holding");
            }
            synchronized (Lock2) {
                System.out.println("Lock2 is holding");
            }
        }
    }

    private static class DeadLockTestDemo2 extends Thread {
        public void run() {
            synchronized (Lock2) {
                System.out.println("Lock2 is holding");
            }
            synchronized (Lock1) {
                System.out.println("Lock1 is holding");
            }
        }
    }
}
