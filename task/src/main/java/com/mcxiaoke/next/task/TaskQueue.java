package com.mcxiaoke.next.task;

import java.util.concurrent.Callable;

/**
 * User: mcxiaoke
 * Date: 15/6/18
 * Time: 12:02
 */
public abstract class TaskQueue implements ITaskQueue {

    static final class SingletonHolder {
        static final TaskQueue INSTANCE = TaskFactory.createQueue(false);
    }

    public static TaskQueue getDefault() {
        return SingletonHolder.INSTANCE;
    }

    public static TaskQueue shared() {
        return SingletonHolder.INSTANCE;
    }

    public static TaskQueue concurrent() {
        return TaskFactory.createQueue(false);
    }

    public static TaskQueue singleThread() {
        return TaskFactory.createQueue(true);
    }

    public static void setDebug(boolean debug) {
        Config.DEBUG = debug;
    }

    abstract <Result> String execute(final Callable<Result> callable,
                                     final TaskCallback<Result> callback,
                                     final Object caller);

    abstract String execute(final Task task);

    abstract void remove(final TaskFuture task);


    /**
     * 取消TAG对应的任务
     *
     * @param task 任务
     * @return 任务是否存在
     */
    abstract boolean cancel(final TaskFuture task);


}
