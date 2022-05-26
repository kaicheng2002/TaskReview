package com.kaicheng.taskReview.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kaicheng.taskReview.entity.SysJob;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/24 18:26
 */
public interface SysJobService extends IService<SysJob> {

    Boolean addJob(SysJob sysJob);

    Boolean updateJob(SysJob sysJob);

    Boolean deleteJob(Integer id);


}
