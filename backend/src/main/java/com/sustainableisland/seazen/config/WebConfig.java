package com.sustainableisland.seazen.config;

import com.sustainableisland.seazen.middleware.RequestLoggingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestLoggingInterceptor requestLoggingInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLoggingInterceptor)
                .addPathPatterns("/api/**")
                // Exclude sensitive endpoints
                .excludePathPatterns(
                        "/api/v1/auth/**", // Authentication
                        "/api/v1/users/password/**", // Password change
                        "/api/v1/users/me" // User data
                );
    }
}