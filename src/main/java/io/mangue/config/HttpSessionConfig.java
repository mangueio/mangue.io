package io.mangue.config;

import io.mangue.util.CookieAndHeaderHttpSessionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableRedisHttpSession
public class HttpSessionConfig extends AbstractHttpSessionApplicationInitializer {

    public HttpSessionConfig() {
        super(HttpSessionConfig.class);
    }

    @Bean
    public HttpSessionStrategy httpSessionStrategy(){
        return  new CookieAndHeaderHttpSessionStrategy();
    }

}