package io.mangue;

import com.mongodb.MongoClient;
import io.mangue.config.Logger;
import io.mangue.models.User;
import io.mangue.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by misael on 15/12/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {MongoClient.class}, initializers = ConfigFileApplicationContextInitializer.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class DataTest {

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test(){
        Logger.debug(mongoClient.toString());
        //TenantContextHolder.
        List<User> users = userRepository.findAll();
        Logger.debug("Bora!!");
    }
}
