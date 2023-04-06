package introductionToHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class P11EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

      entityManager.createQuery("SELECT d.name, MAX(e.salary) " +
                        "FROM Department d JOIN d.employees e GROUP BY d.id " +
                        "HAVING max(e.salary) <= 30000 OR max(e.salary) >= 70000", Object[].class)
                .getResultList().forEach(objects -> System.out.printf("%s %.2f%n",objects[0],(BigDecimal)objects[1]));


        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();
    }
}
