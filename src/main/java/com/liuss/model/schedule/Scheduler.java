package com.liuss.model.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class Scheduler {
    @Scheduled(cron = "0/5 0 * * * ?")
    public void testTasks() throws InterruptedException {
        System.out.println(new Date()+":"+"开始执行...");
        Thread.sleep(10000);
        System.out.println(new Date()+":"+"执行结束...");
    }
    @Scheduled(fixedRate = 2000)
    public void statusCheck() throws InterruptedException {
        System.out.println(new Date()+":"+"状态检查开始执行...");
        Thread.sleep(10000);
        System.out.println(new Date()+":"+"状态检查执行结束...");
    }
}
