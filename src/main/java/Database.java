import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private final String URL = "jdbc:postgresql://localhost:5999/practicum";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, "postgres", "postgres");
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Connection dataSourceConnection() throws SQLException {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setDatabaseName("test");
        ds.setUser("user");
        ds.setPassword("password");
        return ds.getConnection();
    }

    public JdbcTemplate jdbcTemplate() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("practicum");
        ds.setPortNumber(5999);
        ds.setUser("postgres");
        ds.setPassword("postgres");
        return new JdbcTemplate(ds);
    }

    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("practicum");
        ds.setPortNumber(5999);
        ds.setUser("postgres");
        ds.setPassword("postgres");
        return new NamedParameterJdbcTemplate(ds);
    }
}
