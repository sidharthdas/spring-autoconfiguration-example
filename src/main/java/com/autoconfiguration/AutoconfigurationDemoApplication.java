package com.autoconfiguration;

import config.AppConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
/*@ComponentScan(value = {"config"})*/
public class AutoconfigurationDemoApplication implements CommandLineRunner{

    @Autowired
    private WebApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(AutoconfigurationDemoApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig.getName());
    }
}
