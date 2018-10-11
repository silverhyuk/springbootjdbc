package com.silverhyuk.springbootjdbc.runner;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class H2Runner implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(H2Runner.class);

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*Connection connection = dataSource.getConnection();
        logger.info("{} -> {}","URL",connection.getMetaData().getURL());
        logger.info("{} -> {}","UserName",connection.getMetaData().getUserName());

        Statement statement = connection.createStatement();
        String sql = "CREATE TABLE USER(ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
        statement.executeUpdate(sql);
        connection.close();*/
        try(Connection connection = dataSource.getConnection()) {
            logger.info("{} -> {}","URL",connection.getMetaData().getURL());
            logger.info("{} -> {}","UserName",connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE USER (ID INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY (id))";
            statement.executeUpdate(sql);
        }
        jdbcTemplate.execute("INSERT INTO USER VALUES(1, 'eunhyuk')");

    }
}
