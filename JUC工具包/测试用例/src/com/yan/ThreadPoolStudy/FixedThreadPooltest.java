package com.yan.ThreadPoolStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/6/22 10:30
 */

public class FixedThreadPooltest {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i <100 ; i++) {
            executorService.execute(new Task());
        }

    }
}


class Task implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}



