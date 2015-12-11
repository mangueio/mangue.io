package io.mangue.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import io.mangue.config.multitenance.MultiTenantMongoDbFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

import java.net.UnknownHostException;

/**
 * Created by misael on 02/12/2015.
 */

@Configuration
@ConditionalOnClass({ Mongo.class, MongoTemplate.class })
@EnableConfigurationProperties(MongoProperties.class)
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class MongoConfig {
    @Autowired
    private MongoProperties properties;

    @Autowired
    private Environment environment;

    private MongoClient mongo;
//
//    @Autowired
//    private ResourceLoader resourceLoader;
//
//    private ClassLoader classLoader;

    @Bean
    public MultiTenantMongoDbFactory mongoDbFactory(MongoClient mongo) throws Exception {
        String database = this.properties.getMongoClientDatabase();
        return new MultiTenantMongoDbFactory(mongo, database);
    }

    @Autowired(required = false)
    private MongoClientOptions options;

    @Bean
    public MongoClient mongo() throws UnknownHostException {
        this.mongo = this.properties.createMongoClient(this.options, this.environment);
        return this.mongo;
    }

//    @Bean
//    public MongoTemplate mongoTemplate(MongoConverter converter) throws UnknownHostException {
//        this.properties.
//        String database = this.properties.getMongoClientDatabase();
//        MultiTenantMongoDbFactory mongoDbFactory = new MultiTenantMongoDbFactory();
//        return new MongoTemplate(mongoDbFactory, converter);
//    }

}
