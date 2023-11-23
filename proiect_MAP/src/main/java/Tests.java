import Domain.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class Tests {


    public static class UserControllerTest {
        public static void testCreateRead() {
            Controller<User> userController = new Controller<>(UserRepo.getInstance());

            User user1 = new User("user1", "asdfasdf@example.com", "Parola123!", LocalDate.now());
            User user2 = new User("user2", "asdfasdfasf@example.com", "Parola123!", LocalDate.now());

            userController.createObject(user1);
            userController.createObject(user2);

            ArrayList<User> users = userController.readAll();
            assert users.size() == 2;
            assert users.contains(user1);
            assert users.contains(user2);
        }

        public static void testUpdateDelete() {
            Controller<User> userController = new Controller<>(UserRepo.getInstance());

            User user1 = new User("user1", "asdfsdafasd@example.com", "Parola123!", LocalDate.now());
            userController.createObject(user1);

            user1.setUsername("alex");
            userController.updateObject(user1, user1.getId());

            User updatedUser = userController.deleteObject(user1.getId());

            assert updatedUser.getUsername().equals("alex");
        }
    }

    public static class FactoryPatterTest {

        public static void testFactoryUser() {
        UserFactory userFactory = new RegularUserFactory();
        User user1 = userFactory.createUser("user1", "asdfasdf@example.com", "Parola123!", LocalDate.now());
        assert user1.getUserType().equals("User");
        }
        public static void testFactoryAdmin() {
        UserFactory userFactory = new AdminUserFactory();
        User user1 = userFactory.createUser("user1", "asdfasdf@example.com", "Parola123!", LocalDate.now());
        assert user1.getUserType().equals("Admin");
        }
    }

    public static class SingletonTest {

        public static void testSingletone() {
            SongRepo instance1 = SongRepo.getInstance();
            SongRepo instance2 = SongRepo.getInstance();

            assert instance1 == instance2;
        }
    }


}
