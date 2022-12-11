package com.udacity.pricing;

import com.netflix.discovery.DiscoveryClient;
import com.udacity.pricing.entity.Price;
import com.udacity.pricing.repository.PriceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.LongStream;

/**
 * Creates a Spring Boot Application to run the Pricing Service.
 */
@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(PriceRepository repository) {
        return args -> LongStream.range(1,20).forEach(i -> repository.save(new Price( "USD", randomPrice(),i)));
    }

    /**
     * Gets a random price to fill in for a given vehicle ID.
     * @return random price for a vehicle
     */
    private static BigDecimal randomPrice() {
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 5))
                .multiply(new BigDecimal("5000")).setScale(2, RoundingMode.HALF_UP);
    }
}
