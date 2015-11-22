package io.mangue.config;

import io.mangue.interceptors.DomainInterceptor;
import io.mangue.util.ProfileType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
        registry.addInterceptor(new DomainInterceptor());
    }

}
