package com.wuhao.thread.customthreadpool;

/**
 * 任务拒绝策略
 * @param <T>
 */
@FunctionalInterface
interface RejectPolicy<T> {
    void reject(BlockingQueue<T> queue, T task);
}