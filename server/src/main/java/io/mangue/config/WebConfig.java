package io.mangue.config;

import io.mangue.interceptors.DomainInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by misael on 17/10/2015.
 */
@Configuration
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.profiles.active}")
    public String profile;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(domainInterceptor());
    }

    @Bean
    public DomainInterceptor domainInterceptor(){
        return new DomainInterceptor();
    }

}
