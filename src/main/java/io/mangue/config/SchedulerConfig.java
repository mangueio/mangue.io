package io.mangue.config;

//import com.kaviddiss.bootquartz.job.SampleJob;
//import com.kaviddiss.bootquartz.spring.AutowiringSpringBeanJobFactory;
import io.mangue.schedulers.AutowiringSpringBeanJobFactory;
//import liquibase.integration.spring.SpringLiquibase;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfig {

    @Value("${spring.profiles.active}")
    String profile;

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext)
        //    ,
        // injecting SpringLiquibase to ensure liquibase is already initialized and created the quartz tables:
       // SpringLiquibase springLiquibase)
    {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource, JobFactory jobFactory) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);
        factory.setQuartzProperties(quartzProperties());
        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/application-" + profile + ".properties"));
        ClassPathResource cp = new ClassPathResource("/application-" + profile + ".properties");
        Assert.isTrue(cp.exists(), "cant find enviroment config file in classpath");
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

//    @Bean
//    public JobDetailFactoryBean sampleJobDetail() {
//        return createJobDetail(SampleJob.class);
//    }

//    @Bean(name = "sampleJobTrigger")
//    public SimpleTriggerFactoryBean sampleJobTrigger(@Qualifier("sampleJobDetail") JobDetail jobDetail,
//                                                     @Value("${samplejob.frequency}") long frequency) {
//        return createTrigger(jobDetail, frequency);
//    }


}