package com.marketplace.cucumber;

import com.marketplace.MarketPlaceApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MarketPlaceApplication.class, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT)
public abstract class AbstractSpringTests {

}