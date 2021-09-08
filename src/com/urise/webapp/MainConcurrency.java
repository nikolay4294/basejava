package com.urise.webapp;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

    public static final int THREADS_NUMBER = 10_000;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + " , " + getState());
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " , " + Thread.currentThread().getState());
            }
        }).start();
        System.out.println(thread0.getState());

        final MainConcurrency mc = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService completionService = new ExecutorCompletionService(executorService);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->{
 //           Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mc.increment();
                }
                latch.countDown();
                return 5;
            });
            System.out.println(future.isDone());
            //System.out.println(future.get());
/*            thread.start();
            threads.add(thread);*/
       }

  /*      threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
*/      latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        //System.out.println(counter);
        System.out.println(mc.atomicCounter.get());
    }

    private void increment() {
        atomicCounter.incrementAndGet();
 /*       double a = Math.sin(13.0);
        //synchronized (this) {
        lock.lock();
        try {
            counter++;
        } finally {
            lock.unlock();
        }
        //}*/
    }
}
