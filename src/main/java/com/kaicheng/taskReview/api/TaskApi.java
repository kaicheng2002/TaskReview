package com.kaicheng.taskReview.api;

import com.kaicheng.taskReview.common.Y;
import com.kaicheng.taskReview.entity.SysJob;
import com.kaicheng.taskReview.service.SysJobService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/26 20:43
 */
@RestController()
@RequestMapping("/task")
public class TaskApi {

    @Autowired
    private SysJobService sysJobService;

    @PostMapping()
    public Y add(@RequestBody SysJob sysJob) {
        Boolean bool = sysJobService.addJob(sysJob);
        if (bool) {
            return Y.OK();
        } else {
            return Y.ERROR("任务已存在");
        }
    }

    @PutMapping()
    public Y update(@RequestBody SysJob sysJob) {
        Boolean bool = sysJobService.updateJob(sysJob);
        if (bool) {
            return Y.OK();
        } else {
            return Y.ERROR("修改失败");
        }
    }

    @DeleteMapping("/{id}")
    public Y delete(@PathVariable("id") Integer id) {
        Boolean bool = sysJobService.deleteJob(id);
        return Y.OK();
    }
}
