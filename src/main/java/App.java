import domain.JdbcTemplateRepo;
import domain.User;
import domain.UserRepo;

import java.util.List;

public class App {

    public static void main(String[] args) {
//        User user = new User("test", "test", "888888");
        JdbcTemplateRepo userRepo = new JdbcTemplateRepo();
        UserRepo nativeRepo = new UserRepo();
        nativeRepo.deleteById(3L);
        userRepo.deleteById(5L);
//        User userFromDb = userRepo.createUser(user);
//        System.out.println(userFromDb.getId());
//        User user = userRepo.getById(6L);
        //System.out.println(user.getPhone());

        List<User> users = userRepo.getAll();
        System.out.println(users.size());
    }
}
