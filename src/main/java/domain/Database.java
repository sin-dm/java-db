package domain;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String URL = "jdbc:postgresql://localhost:5999/practicum";

    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД");
        }
    }

    protected Connection dataSourceConnection() {
        try {
            PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setServerName("localhost");
            ds.setDatabaseName("practicum");
            ds.setPortNumber(5999);
            ds.setUser("postgres");
            ds.setPassword("postgres");
            return ds.getConnection();
        }  catch (SQLException e) {
            throw new RuntimeException("Не удалось подключиться к БД");
        }
    }

    protected NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("practicum");
        ds.setPortNumber(5999);
        ds.setUser("postgres");
        ds.setPassword("postgres");
        return new NamedParameterJdbcTemplate(ds);
    }

    protected JdbcTemplate jdbcTemplate() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("practicum");
        ds.setPortNumber(5999);
        ds.setUser("postgres");
        ds.setPassword("postgres");
        return new JdbcTemplate(ds);
    }


}
