package com.kaicheng.taskReview.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kaicheng.taskReview.config.CronTaskRegistrar;
import com.kaicheng.taskReview.config.SchedulingRunnable;
import com.kaicheng.taskReview.dao.SysJobDao;
import com.kaicheng.taskReview.entity.SysJob;
import com.kaicheng.taskReview.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/24 18:27
 */
@Service("sysJobService")
public class SysJobServiceImpl extends ServiceImpl<SysJobDao, SysJob> implements SysJobService {

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public Boolean addJob(SysJob sysJob) {
        List<SysJob> sysJobs = baseMapper.selectList(new QueryWrapper<SysJob>());
        for (SysJob job : sysJobs) {
            if (job.equals(sysJob)) {
                return false;
            }
        }

        baseMapper.insert(sysJob);
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        if (sysJob.getJobStatus() == 1) {
            cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
        }
        return true;
    }

    @Override
    public Boolean updateJob(SysJob sysJob) {
        baseMapper.updateById(sysJob);
        if (sysJob != null) {
            SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
            if (sysJob.getJobStatus() == 1) {
                cronTaskRegistrar.addCronTask(schedulingRunnable, sysJob.getCronExpression());
            } else {
                cronTaskRegistrar.removeCronTask(schedulingRunnable);
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteJob(Integer id) {
        SysJob sysJob = baseMapper.selectById(id);
        SchedulingRunnable schedulingRunnable = new SchedulingRunnable(sysJob.getBeanName(), sysJob.getMethodName(), sysJob.getMethodParams());
        cronTaskRegistrar.removeCronTask(schedulingRunnable);
        baseMapper.deleteById(id);
        return true;
    }


    void testTask() {
        System.out.println(new Date());
    }
}
