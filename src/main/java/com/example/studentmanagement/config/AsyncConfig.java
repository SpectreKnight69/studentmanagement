package com.example.studentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public AsyncTaskExecutor taskExecutor() {
        // Base thread pool configuration
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);       // 🔁 number of core threads
        executor.setMaxPoolSize(20);        // 🔝 max threads
        executor.setQueueCapacity(50);      // ⏳ queue before rejecting
        executor.setThreadNamePrefix("AsyncExecutor-");
        executor.initialize();

        // Wrap executor to propagate Spring Security context
        return new DelegatingSecurityContextAsyncTaskExecutor(executor);
    }
}
