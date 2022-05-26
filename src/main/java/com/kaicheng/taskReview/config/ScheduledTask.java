package com.kaicheng.taskReview.config;

import java.util.concurrent.ScheduledFuture;

/**
 * @Author: Hankaicheng
 * @Date: 2022/5/24 19:05
 */
public final class ScheduledTask {

    volatile ScheduledFuture<?> future;

    public void cancel() {
        ScheduledFuture<?> future = this.future;
        if (future != null) {
            future.cancel(true);
        }
    }
}
