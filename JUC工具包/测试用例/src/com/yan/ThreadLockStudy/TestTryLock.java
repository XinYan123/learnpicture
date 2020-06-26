package com.yan.ThreadLockStudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/6/26 10:52
 */
public class TestTryLock {

    int flag = 1;
    static Lock l1 = new ReentrantLock();
    static Lock l2 = new ReentrantLock();



}
