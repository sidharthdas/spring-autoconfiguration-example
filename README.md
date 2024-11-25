
# spring-autoconfiguration-example

The main difference between @Configuration and @AutoConfiguration is in autoconfiguration proxyBeanMethods = false, whereas in configuration by default proxyBeanMethods = true. When proxyBeanMethods = true, it is a bit costly during startup, as it will create a proxyBean.


Spring uses the Code Generation library (CGLIB) to generate the proxyBean of that class, so if we call the bean method, again and again, it will not create a new bean, instead, it will give you a proxy bean that Spring has created.

proxyBeanMethods = true :

Suppose we have the below code:

```
@Bean
public RestTemplate getRestTemplate() {
      return new RestTemplate();
}

@Bean
public A getA() {
return new A(getRestTemplate());
}
@Bean
public B getB() {
return new B(getRestTemplate());
}
```
in the above code, in a configuration class, we have 3 beans but beans a and b are calling getRestTemplate class, but with ``` proxyBeanMethods = true ``` the getRestTemplate method will get called only once and spring will keep the proxy.
but if we use @AutoConfiguration or @Configutation(proxyBeanMethods = false), this code will give a compilation error, as we disabled the ``` proxyBeanMethods = false ```. To fix this we can have

```
@Bean
public RestTemplate getRestTemplate() {
      return new RestTemplate();
}

@Bean
public A getA(RestTemplate restTemplate) {
return new A(restTemplate);
}
@Bean
public B getB(RestTemplate restTemplate) {
return new B(restTemplate);
}

```

1. Main use if we don't have to use @ComponentScan if we are using our module in another project.

   Steps:
   1. Create a folder in the resources folder i.e. META-INF
   2. Inside META-INF, create another folder i.e. spring
   3. Then inside the spring folder, create a file with the name `org.springframework.boot.autoconfigure.AutoConfiguration.imports`
   4. Inside the file add the class which is annotated with @AutoConfiguration
      Example :
      `config.AppConfig`
   5. There are other useful annotations such as @ConditionalOnClass and @ConditionalOnBean
        1. @ConditionalOnClass - This annotation is at class level. If the class is not present then the class with AutoConfiguration will not be created
           Example : `@ConditionalOnClass(name = "com.autoconfiguration.config.TestConfig")`
        3. @ConditionalOnBean - This annotation is at the method level. If the bean is not present then the bean in @AutoConfiguration class will not be created
           Example : `@ConditionalOnBean(name = "getStudent")`


  Overall Example : 

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
