import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Tests.UserControllerTest.testCreateRead();
        Tests.UserControllerTest.testUpdateDelete();
        Tests.FactoryPatterTest.testFactoryUser();
        Tests.FactoryPatterTest.testFactoryAdmin();
        Tests.SingletonTest.testSingletone();
        UI.startUI();

    }
}