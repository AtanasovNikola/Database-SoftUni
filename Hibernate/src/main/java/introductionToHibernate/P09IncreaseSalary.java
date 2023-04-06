package introductionToHibernate;

import introductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class P09IncreaseSalary {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();


        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();


        for (Employee employee : resultList) {
            BigDecimal newSalary = employee.getSalary().multiply(BigDecimal.valueOf( 1.12));
            employee.setSalary(newSalary);
            entityManager.merge(employee);
        }

        entityManager.getTransaction().commit();

        for (Employee employee : resultList) {
            System.out.println(employee);
        }

    }
}
