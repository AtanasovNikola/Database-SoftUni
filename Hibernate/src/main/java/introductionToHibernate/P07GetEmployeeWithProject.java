package introductionToHibernate;

import introductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P07GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int employeeId = Integer.parseInt(scanner.nextLine());

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        entityManager.createQuery("FROM Employee e" +
                " WHERE e.id = :employeeId", Employee.class)
                .setParameter("employeeId", employeeId)
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
    }
}
