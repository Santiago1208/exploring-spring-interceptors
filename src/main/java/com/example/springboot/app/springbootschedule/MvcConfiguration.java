package com.example.springboot.app.springbootschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

	@Autowired
	@Qualifier("scheduleInterceptor")
	private HandlerInterceptor scheduleInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration registration = registry.addInterceptor(scheduleInterceptor);
		// Always the alternative paths must be excluded to be intercepted.
		registration.excludePathPatterns("/closed");
	}
	
}
