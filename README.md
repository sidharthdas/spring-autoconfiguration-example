# spring-autoconfiguration-example

1. Main use if we dont have to do the componentScan if we are using our module in other project.

   Steps:
   1. Create a folder in resources folder i.e. META-INF
   2. Inside META-INF, create another folder i.e. spring
   3. Then inside spring folder, create a file with name `org.springframework.boot.autoconfigure.AutoConfiguration.imports`
   4. Inside the file add the class which is annotated with @AutoConfiguration
      Example :
      `config.AppConfig`
   5. There are other useful annotation such as @ConditionalOnClass and @ConditionalOnBean
        1. @ConditionalOnClass - This annotation is at class level. If the class is not present then the class with AutoConfiguration will not be created
           Example : `@ConditionalOnClass(name = "com.autoconfiguration.config.TestConfig")`
        3. @ConditionalOnBean - This annotation is at method level. If the bean is not present then the bean in AutoConfiguration class will not be created
           Example : `@ConditionalOnBean(name = "getStudent")`


  Overal Example : 

  ```
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
  ```
