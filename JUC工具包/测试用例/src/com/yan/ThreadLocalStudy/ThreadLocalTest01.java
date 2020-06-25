package com.yan.ThreadLocalStudy;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.SimpleFormatter;

/*
场景一的测试用例
 */

public class ThreadLocalTest01 {
    //第一步构建线程池
    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        //获取ThreadLocal的对象
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.simpleFormatThreadLocal.get();
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        for (int i = 0; i <1000 ; i++) {
            int finalI = i;

            //开启线程池
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    String date = new ThreadLocalTest01().date(finalI);
                    System.out.println(date);
                }
            });
        }

        //关闭线程池
        executorService.shutdown();
    }
}

class ThreadSafeFormatter {
        public static ThreadLocal<SimpleDateFormat> simpleFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
            //使用ThreadLocal的要重写initialValue()
            @Override
            protected SimpleDateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
        };
    }



