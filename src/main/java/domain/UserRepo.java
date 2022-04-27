package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepo extends Database {
    //getById
    //getAll
    //create
    //update
    //delete

    public void deleteById(Long id) {
        String sql = "DELETE FROM public.user WHERE id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении");
        }
    }

    public List<User> getAllUser() {
        String sql = "SELECT * FROM public.user;";
        try {
            List<User> users = new ArrayList<>();
            Connection conn = dataSourceConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isClosed()) {
                while (rs.next()) {
                    users.add(
                            new User(
                                    rs.getLong("id"),
                                    rs.getString("name"),
                                    rs.getString("surname"),
                                    rs.getString("phone")
                            )
                    );
                }
                return users;
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить юзеров");
        }
        return null;
    }

    public User getById(Long id) {
        String sql = "SELECT * FROM public.user WHERE id = ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("phone")
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось получить юзера");
        }
        return null;
    }

    public User createUser(User user) {
        String sql = "INSERT INTO public.user (name, surname, phone) VALUES (?,?,?) RETURNING id;";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getPhone());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                user.setId(id);
                return user;
            }
        } catch (Exception e) {
            throw new RuntimeException("Не удалось создать юзера");
        }
        return null;
    }

}
