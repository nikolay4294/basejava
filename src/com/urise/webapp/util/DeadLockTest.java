package com.urise.webapp.util;

class DeadLockTest {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        executeDeadLock(Lock1, Lock2);
    }

    private static void executeDeadLock(Object Lock1, Object Lock2) {
        Thread thread0 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток " + Thread.currentThread().getName() + " ожидает захвата объекта Lock1");
                synchronized (Lock1) {
                    System.out.println("поток " + Thread.currentThread().getName() + " захватил объект Lock1");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("поток " + Thread.currentThread().getName() + " ожидает захвата объекта Lock2");
                    synchronized (Lock2) {
                        System.out.println("поток " + Thread.currentThread().getName() + " захватил объект Lock2");
                    }
                }
            }
        });
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("поток " + Thread.currentThread().getName() + " ожидает захвата объекта Lock2");
                synchronized (Lock2) {
                    System.out.println("поток " + Thread.currentThread().getName() + " захватил объект Lock2");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("поток " + Thread.currentThread().getName() + " ожидает захвата объекта Lock1");
                    synchronized (Lock1) {
                        System.out.println("поток " + Thread.currentThread().getName() + " захватил объект Lock1");
                    }
                }
            }
        });
        thread0.start();
        thread1.start();
    }
}