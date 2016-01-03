package io.mangue;

import com.mongodb.MongoClient;
import org.junit.runner.RunWith;
import org.springframework.boot.test.ConfigFileApplicationContextInitializer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by misael on 1/2/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MongoClient.class}, initializers = ConfigFileApplicationContextInitializer.class)
//@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class Test {

    @org.junit.Test
    public void test(){
    }
}
