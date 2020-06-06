package com.yan;

import jdk.internal.org.objectweb.asm.tree.MultiANewArrayInsnNode;

import javax.crypto.interfaces.PBEKey;
import java.util.*;

/**
 * 用wait/notify来实现
 *
 * 理解这个程序的关键是，消费者有没有进入wait状态
 *
 */
public class test04 {

    public static void main(String[] args) throws InterruptedException {

        EventStorage eventStorage =new EventStorage();

        Producer producer = new Producer(eventStorage);
        Customer customer = new Customer(eventStorage);


        new Thread(customer).start();

        Thread.sleep(1000);
        new Thread(producer).start();


    }

}



    //生产者
    class Producer implements Runnable {

        private EventStorage storage; //组合

        public Producer(EventStorage storage){
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                storage.put();

//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        }
    }

    //消费者
    class Customer implements Runnable {

        private EventStorage storage; //组合

        public Customer(EventStorage storage){
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 300; i++) {
                storage.take();
            }
        }
    }


    //相当于一个仓库
    class EventStorage {
        private int maxSize;
        private LinkedList<Date> storage;


        public EventStorage() {
            maxSize = 30;
            storage = new LinkedList<>();

        }
        //生产者
        public synchronized void put() {
            //如果东西满了，仓库不能放东西，要wait
            while (storage.size() == maxSize){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //东西没满就继续放到仓库
            storage.add(new Date());
            System.out.println("仓库里有了" + storage.size() + "个产品");

            //这个唤醒是唤醒正在wait的线程，但是具体哪一个就不清楚，
            //但是这个案例中只可能有一个被唤醒的线程
            //就是此时进入wait的消费者线程
            notify();
        }


        //消费者
        public synchronized void take() {
            //如果仓库没东西，仓库不能拿，要wait，等待生产者往里面放
            while (storage.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("拿到了"+storage.poll()+"现在还剩下"+storage.size());

            //这个唤醒是唤醒正在wait的线程，但是具体哪一个就不清楚，
            //但是这个案例中只可能有一个被唤醒的线程
            //就是此时进入wait的生产者线程
            notify();
        }

    }








