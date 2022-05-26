package com.kaicheng.taskReview.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/24 10:34
 */
@Data
@TableName("sys_job")
public class SysJob {

    private Integer id;

    private String beanName;

    private String methodName;

    private String methodParams;

    private String cronExpression;

    private Integer jobStatus;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysJob sysJob = (SysJob) o;
        return Objects.equals(beanName, sysJob.beanName) && Objects.equals(methodName, sysJob.methodName) && Objects.equals(methodParams, sysJob.methodParams) && Objects.equals(cronExpression, sysJob.cronExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(beanName, methodName, methodParams, cronExpression);
    }
}
