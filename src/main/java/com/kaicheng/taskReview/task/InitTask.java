package com.kaicheng.taskReview.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kaicheng.taskReview.config.CronTaskRegistrar;
import com.kaicheng.taskReview.config.SchedulingRunnable;
import com.kaicheng.taskReview.entity.SysJob;
import com.kaicheng.taskReview.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/26 21:23
 */
@Component
public class InitTask implements CommandLineRunner {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Autowired
    private SysJobService sysJobService;

    @Override
    public void run(String... args) throws Exception {
        List<SysJob> jobs = sysJobService.list(new QueryWrapper<SysJob>().eq("job_status", 1));
        jobs.forEach(sysJob ->{
            cronTaskRegistrar.addCronTask(new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams()), sysJob.getCronExpression());
        });

    }
}
