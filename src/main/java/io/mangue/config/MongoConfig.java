package io.mangue.config;

import com.mongodb.MongoClient;
import io.mangue.config.multitenance.MultiTenantMongoDbFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by misael on 02/12/2015.
 */
public class MongoConfig {
    @Autowired
    private MongoProperties properties;

    @Autowired
    private Environment environment;

    @Autowired
    private ResourceLoader resourceLoader;

    private ClassLoader classLoader;

    @Bean
    public MultiTenantMongoDbFactory mongoDbFactory(MongoClient mongo) throws Exception {
        String database = this.properties.getMongoClientDatabase();
        return new MultiTenantMongoDbFactory(mongo, database);
    }

}
