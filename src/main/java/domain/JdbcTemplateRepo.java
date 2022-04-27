package domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplateRepo extends Database {

    NamedParameterJdbcTemplate namedParameterJdbcTemplate = namedParameterJdbcTemplate();
    JdbcTemplate jdbcTemplate = jdbcTemplate();

    public User createUser(User user) {
        String sql = "INSERT INTO public.user (name, surname, phone) VALUES (:name,:surname,:phone) RETURNING id;";
        Long id = namedParameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource()
                        .addValue("name", user.getName())
                        .addValue("surname", user.getSurname())
                        .addValue("phone", user.getPhone()),
                Long.class
        );
        user.setId(id);
        return user;
    }

    public User getById(Long id) {
        String sql = "SELECT * FROM public.user WHERE id = :id";
        return namedParameterJdbcTemplate.queryForObject(
                sql,
                new MapSqlParameterSource().addValue("id", id),
                new UserRowMapper()
        );
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM public.user WHERE id = ?", id);
    }


    public List<User> getAll() {
        String sql = "SELECT * FROM public.user";
        return jdbcTemplate.query(
                sql,
                new UserRowMapper()
        );
    }

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("surname"),
                    rs.getString("phone")
            );
        }
    }
}
