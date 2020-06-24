package com.yan.ThreadPoolStudy;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: Frankcrose
 * @createDate: 2020/6/22 10:53
 */
public class SingleThreadExecutortest {

        private static ExecutorService executorService = Executors.newSingleThreadExecutor();

        public static void main(String[] args) {
        /*
        这个是Integer类中的一个int类型的常量MAX_VALUE
        它代表int所能表示的最大值 0x7FFFFFFF
         */
            for (int i = 0; i <Integer.MAX_VALUE ; i++) {
                executorService.execute(new Task());
            }
        }

    }

