package com.yan.ThreadPoolStudy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Frankcrose
 * 内存溢出测试
 * -Xmx4m -Xms4m
 */
public class FixedThreadPoolOOMTest {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        /*
        这个是Integer类中的一个int类型的常量MAX_VALUE
        它代表int所能表示的最大值 0x7FFFFFFF
         */
        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            executorService.execute(new TestQueue());
        }
    }
}

class TestQueue implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(100000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}
