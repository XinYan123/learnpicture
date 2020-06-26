package com.yan.ThreadLockStudy;

import java.util.concurrent.locks.ReentrantLock;

/**
 *可重入锁的实现
 */
public class BuyTicket implements Runnable {

    public static void main(String[] args) {
        BuyTicket bt = new BuyTicket();

        Thread t1 = new Thread(bt);
        Thread t2 = new Thread(bt);
        Thread t3 = new Thread(bt);

        t1.start();
        t2.start();
        t3.start();
    }


    //构建锁
    private static ReentrantLock l = new ReentrantLock();

    //买票方法
//    private static void  buy(){
//        //上锁
//        l.lock();
//        try {
//            System.out.println(Thread.currentThread().getName()+"打开APP买票选座位");
//            Thread.sleep(100);
//            System.out.println(Thread.currentThread().getName()+"我选好票了，并且付钱");
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//
//        }finally {
//            //放锁
//            l.unlock();
//        }
//    }
    @Override
    public void run() {
        //上锁
        l.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"打开APP买票选座位");
            Thread.sleep(100);
            System.out.println(Thread.currentThread().getName()+"我选好票了，并且付钱");

        } catch (InterruptedException e) {
            e.printStackTrace();

        }finally {
            //放锁
            l.unlock();
        }
    }
}
