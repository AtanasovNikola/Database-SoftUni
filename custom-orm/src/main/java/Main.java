import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Scanner;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {

    public static void main(String[] args) throws SQLException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        createConnection("root", "Gishmi11!", "custom_orm");
        Connection connection = getConnection();

        EntityManager<User> userEntityManager = new EntityManager<>(connection);
String username = scanner.nextLine();
String password = scanner.nextLine();
int age = Integer.parseInt(scanner.nextLine());

        try {
            User user = new User(username, password, age, LocalDate.now());
            userEntityManager.persist(user);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("User with this username, already exist");
        }

    }
}