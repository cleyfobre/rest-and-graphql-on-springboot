package com.demo.springgraphql2.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.demo.springgraphql2.repository" }, entityManagerFactoryRef = "myEntityManagerFactory", transactionManagerRef = "myTransactionManager")
public class DBConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    DataSourceProperties myDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    DataSource myDataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean(name = "myEntityManagerFactory")
    @Primary
    LocalContainerEntityManagerFactoryBean myEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(myDataSource()).packages("com.demo.springgraphql2.entity").build();
    }

    @Bean
    @Primary
    PlatformTransactionManager myTransactionManager(
            final @Qualifier("myEntityManagerFactory") LocalContainerEntityManagerFactoryBean myEntityManagerFactory) {
        return new JpaTransactionManager(myEntityManagerFactory.getObject());
    }

}
