package config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(name = "com.autoconfiguration.config.TestConfig")
// If this class is not present then this bean will not be created
public class AppConfig {

    @Bean
    @ConditionalOnBean(name = "getStudent") // If this bean is not present, then getName bean will not be created
    public String getName() {
        return "Hello World";
    }
}
