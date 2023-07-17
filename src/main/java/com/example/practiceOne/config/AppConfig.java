package com.example.practiceOne.config;

import com.example.practiceOne.service.AppService;
import com.example.practiceOne.aop.DatabaseIntegrityAspect;
import com.example.practiceOne.aop.ExceptionHandlerAspect;
import com.example.practiceOne.utils.CustomerMappingUtils;
import com.example.practiceOne.repository.CustomerRepository;
import com.example.practiceOne.utils.FlightMappingUtils;
import com.example.practiceOne.repository.FlightRepository;
import com.example.practiceOne.utils.TicketMappingUtils;
import com.example.practiceOne.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

    private static Logger logger = LoggerFactory.getLogger(AppConfig.class);

    @Bean
    public CustomerRepository customerRepository() {
        return new CustomerRepository();
    }

    @Bean
    public FlightRepository flightRepository() {
        return new FlightRepository();
    }

    @Bean
    public TicketRepository ticketRepository() {
        return new TicketRepository();
    }

    @Bean
    public AppService appService() {
        return new AppService();
    }

    @Bean
    public CustomerMappingUtils customerMappingUtils() {
        return new CustomerMappingUtils();
    }

    @Bean
    public FlightMappingUtils flightMappingUtils() {
        return new FlightMappingUtils();
    }

    @Bean
    public TicketMappingUtils ticketMappingUtils() {
        return new TicketMappingUtils();
    }

    @Bean
    public DataSource dataSource() {
        try {
            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
            return dbBuilder.setType(EmbeddedDatabaseType.H2)
                    .addScripts("classpath:db/h2/schema.sql",
                            "classpath:db/h2/test-data.sql").build();
        } catch (Exception e) {
            logger.error("Embedded DataSource bean cannot be created!", e);
            return null;
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public ExceptionHandlerAspect exceptionHandlerAspect() {
        return new ExceptionHandlerAspect();
    }

    @Bean
    public DatabaseIntegrityAspect databaseIntegrityAspect() {
        return new DatabaseIntegrityAspect();
    }

}
