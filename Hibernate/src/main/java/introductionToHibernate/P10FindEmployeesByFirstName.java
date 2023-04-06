package introductionToHibernate;

import introductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P10FindEmployeesByFirstName {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        Scanner scanner = new Scanner(System.in);

        String pattern = scanner.nextLine();

        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :pattern", Employee.class)
                .setParameter("pattern",pattern + "%")
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
    }
}
