package org.example.naturalidsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class NaturalIdSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NaturalIdSampleApplication.class, args);
    }

}
