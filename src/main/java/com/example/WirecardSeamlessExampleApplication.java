package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

//@SpringBootApplication
@PropertySources({
        @PropertySource("classpath:properties/wirecard.properties")
})
@ComponentScan({"com.wirecard", "com.example"})
@Configuration
//@ComponentScan
@EnableAutoConfiguration
public class WirecardSeamlessExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WirecardSeamlessExampleApplication.class, args);
    }
}
