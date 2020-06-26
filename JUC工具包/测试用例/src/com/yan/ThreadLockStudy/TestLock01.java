package com.yan.ThreadLockStudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/6/26 10:46
 */
public class TestLock01 {
    //实例化一个lock锁
    private  static Lock testLock = new ReentrantLock();

    public static void main(String[] args) {
        //上锁
        testLock.lock();
        try {
            System.out.println(Thread.currentThread().getName());

        }finally {
            testLock.unlock();
        }
    }


}
