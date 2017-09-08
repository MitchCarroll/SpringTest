package com.asiwi.test.mitch.SpringTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class SpringTestApplication {
	@Bean
	public MyBean myBean() {
		return new MyBean();
	}
	
	@Bean(name="taskExecutor")
	public TaskExecutor workExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        threadPoolTaskExecutor.setCorePoolSize(3);
        threadPoolTaskExecutor.setMaxPoolSize(3);
        threadPoolTaskExecutor.setQueueCapacity(600);
        threadPoolTaskExecutor.afterPropertiesSet();
        return threadPoolTaskExecutor;
    }

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
		MyBean bean = context.getBean(MyBean.class);
		
		System.out.println("MAIN: Starting Test...");
		bean.test();
		System.out.println("MAIN: Ending Test...");
	}
}
