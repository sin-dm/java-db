
public class App {

    public static void main(String[] args) {
        UserRepo repo = new UserRepo();
        User user = repo.createUser(new User("test", "test", "phone"));
        repo.getAllUsers();
        repo.deleteById(user.getId());
    }
}
