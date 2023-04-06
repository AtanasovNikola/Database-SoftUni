package introductionToHibernate;

import introductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P04EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        String departmentName = "Research and Development";
        entityManager.createQuery("SELECT e FROM Employee e WHERE e.department.name = :departmentName" +
                " ORDER BY e.salary ASC, e.id", Employee.class)
                .setParameter("departmentName", departmentName)
                .getResultStream()
                .forEach(e -> {
            String format = String.format("%s %s from %s - $%f"
                    , e.getFirstName(), e.getLastName(), departmentName, e.getSalary());

            System.out.println(format);
        });

        entityManager.getTransaction().commit();
    }
}
