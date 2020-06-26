package com.yan.ThreadLockStudy;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试公平与非公平锁
 */
public class FairLock {


    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread thread[] = new Thread[10];

        for (int i = 0; i < 10; i++) {
            //每个线程传入同一个printQueue对象
            thread[i] = new Thread(new Job(printQueue));
        }


        for (int i = 0; i < 10; i++) {
            thread[i].start();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}

class Job implements Runnable {

    PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "开始打印");
        //传入对象到打印的方法中
        printQueue.printJob(new Object());

        System.out.println(Thread.currentThread().getName() +"打印完毕");
    }
}


class PrintQueue {

    //非公平锁设置为false
    private Lock l1 = new ReentrantLock(false);
    //公平锁设置为true
    private Lock l2 = new ReentrantLock(true);


    public void printJob(Object document) {
        l1.lock();
//        l2.lock();
        try {
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"正在打印需要" + duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l1.unlock();
//            l2.unlock();
        }


        l1.lock();
//        l2.lock();
        try {
            int duration = new Random().nextInt(10)+1;
            System.out.println(Thread.currentThread().getName()+"正在打印需要" + duration+"秒");
            Thread.sleep(duration*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            l1.unlock();
//            l2.unlock();
        }



    }

}
