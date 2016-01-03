package io.mangue.config;

import org.reflections.Reflections;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.http.MediaType;

import java.util.Set;

@Configuration
public class RestDataConfig  extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        super.configureRepositoryRestConfiguration(config);
        Reflections reflections = new Reflections("io.mangue.models");
        Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Document.class);
        config.exposeIdsFor(entities.toArray(new Class<?>[0]));
        config.setBasePath("/data");
    }
}