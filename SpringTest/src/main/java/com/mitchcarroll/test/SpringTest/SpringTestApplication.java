package com.mitchcarroll.test.SpringTest;

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
	@Bean("TestBean")
	public MyBean myBean() {
		return new MyBean();
	}
	
	@Bean("WebBean")
	public MyBean webBean() {
		return new MyBean("WEB");
	}

	@Bean("DbBean")
	public MyDBBean dbBean() {
		return new MyDBBean();
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
		System.out.println("MAIN: Starting Application...");
		ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
		System.out.println("MAIN: Initializing test bean...");
		MyBean bean = (MyBean) context.getBean("TestBean");
		bean.setValue("test");
		System.out.println("MAIN: Test bean initialized.");
		System.out.println("MAIN: Starting Test...");
		bean.test();
		System.out.println("MAIN: Ending Test...");
	}
}
