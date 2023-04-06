package introductionToHibernate;

import javax.persistence.*;
import java.util.Scanner;

public class P02ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] fullName = scanner.nextLine().split("\\s+");

        int result = em.createQuery
                        ("SELECT count(e) FROM Employee e" +
                                " WHERE e.firstName = :first_name" +
                                " AND e.lastName = :last_name", Long.class)
                .setParameter("first_name", fullName[0])
                .setParameter("last_name", fullName[1]).getFirstResult();

        String output = result == 0 ? "No" : "Yes";
        System.out.println(output);
        em.getTransaction().commit();
    }
}
