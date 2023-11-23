import java.time.LocalDate;
import Domain.User;

public interface UserFactory {
    User createUser(String username, String email, String password, LocalDate dateOfBirth);
}

class RegularUserFactory implements UserFactory {
    @Override
    public User createUser(String username, String email, String password, LocalDate dateOfBirth) {
        User user = new User(username, email, password, dateOfBirth);
        user.setUserType("User");
        return user;
    }

}

class AdminUserFactory implements UserFactory {
    @Override
    public User createUser(String username, String email, String password, LocalDate dateOfBirth) {
        User user = new User(username, email, password, dateOfBirth);
        user.setUserType("Admin");
        return user;
    }

}