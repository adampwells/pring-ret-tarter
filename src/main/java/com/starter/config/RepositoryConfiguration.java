package com.starter.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by adam.wells on 1/03/2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.starter.model"})
@EnableJpaRepositories(basePackages = {"com.starter.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {
}