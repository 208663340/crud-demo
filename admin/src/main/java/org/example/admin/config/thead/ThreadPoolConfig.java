package org.example.admin.config.thead;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * 线程池配置
 *
 * @Author: Administrator
 **/
@Configuration
public class ThreadPoolConfig {
    /**
     * 核心线程池大小
     */
    private int corePoolSize = 50;

    /**
     * 最大可创建的线程数
     */
    private int maxPoolSize = 200;

    /**
     * 队列最大长度
     */
    private int queueCapacity = 1000;

    /**
     * 线程池维护线程所允许的空闲时间
     */
    private int keepAliveSeconds = 300;

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setThreadNamePrefix("MyThreadPool-");
        // 线程池对拒绝任务(无线程可用)的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

//    /**
//     * 执行周期性或定时任务
//     * @return 定时任务
//     */
//    @Bean(name = "scheduledExecutorService")
//    protected ScheduledExecutorService scheduledExecutorService() {
//        return new ScheduledThreadPoolExecutor(corePoolSize,
//                new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build()) {
//            @Override
//            protected void afterExecute(Runnable r, Throwable t) {
//                super.afterExecute(r, t);
//                Threads.printException(r, t);
//            }
//        };
//    }

    /**
     * 异步任务
     *
     * @return 异步任务线程池
     */
    @Bean(name = "asyncTask")
    protected SimpleAsyncTaskExecutor asyncTask() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
        simpleAsyncTaskExecutor.setThreadFactory(threadPoolTaskExecutor());
        return simpleAsyncTaskExecutor;
    }

//    /**
//     * Socket连接线程池
//     * @return 线程池
//     */
//    @Bean(name = "socketThreadPool")
//    protected ThreadPoolExecutor socketInfoThreadPool() {
//        ThreadFactory socketThreadFactory = new ThreadFactoryBuilder().setNameFormat("ins-data-info-%d").build();
//        return new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveSeconds, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), socketThreadFactory);
//    }
//    @Bean(name = "socketThreadPoolTest")
//    protected ThreadPoolExecutor socketInfoThreadPoolTest(){
//        ThreadFactory socketThreadFactory = new ThreadFactoryBuilder().setNameFormat("ins-data-info-test-%d").build();
//        return new ThreadPoolExecutor(10, 10, 300, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), socketThreadFactory);
//    }
}
