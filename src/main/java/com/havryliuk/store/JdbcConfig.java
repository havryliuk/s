package com.havryliuk.store;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.havryliuk.store.dao.CartDao;
import com.havryliuk.store.dao.CustomerDao;
import com.havryliuk.store.dao.OrderDao;
import com.havryliuk.store.dao.ProductDao;
import com.havryliuk.store.dao.UserDao;

@Configuration
@PropertySource("classpath:db.properties")
public class JdbcConfig {
    @Value("${db.driver}")
    private String driverClassName;
    @Value("${db.url}")
    private String url;
    @Value("${db.username}")
    private String username;
    @Value("${db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource =  new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public CustomerDao customerDao() {
        return new CustomerDao(jdbcTemplate(dataSource()));
    }

    @Bean
    public ProductDao productDao() {
        return new ProductDao(jdbcTemplate(dataSource()));
    }

    @Bean
    public CartDao cartDao() {
        return new CartDao(jdbcTemplate(dataSource()));
    }

    @Bean
    public OrderDao orderDao() {
        return new OrderDao(jdbcTemplate(dataSource()), productDao());
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcTemplate(dataSource()));
    }
}