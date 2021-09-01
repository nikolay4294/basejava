package com.urise.webapp.util;

class DeadLockTest {
    private static final Object Lock1 = new Object();
    private static final Object Lock2 = new Object();

    public static void main(String[] args) {
        executeDeadLock(Lock1, Lock2);
        executeDeadLock(Lock2, Lock1);
    }

    private static void executeDeadLock(Object Lock1, Object Lock2) {
        new Thread(() -> {
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
        }).start();
    }
}