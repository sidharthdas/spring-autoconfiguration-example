package com.autoconfiguration;

import config.AppConfig;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
/*@ComponentScan(value = {"config"})*/
public class AutoconfigurationDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutoconfigurationDemoApplication.class, args);
        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println(appConfig.getName());

    }
}
