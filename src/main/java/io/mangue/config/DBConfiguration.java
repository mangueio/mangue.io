package io.mangue.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfiguration {

   @Bean(name = "quartz")
   @ConfigurationProperties(prefix = "spring.datasource.quartz")
   public DataSource quartzDataSource() {
     return DataSourceBuilder.create().build();
   }

}
