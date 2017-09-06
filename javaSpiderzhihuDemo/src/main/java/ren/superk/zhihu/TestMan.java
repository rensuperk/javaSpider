package ren.superk.zhihu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestMan {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        while (true)
        scheduledExecutorService.schedule(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },5,TimeUnit.SECONDS);


    }
}
