package _03_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

Student student = new Student("Nikola","Atanasov","0898824632",4.50,"50%");
Student student2 = new Student("Petyr","Iliev","0899915853",5.50,"60%");
Teacher teacher = new Teacher("Atanas","Linkov","0877519658","atanas_linkov@gmail.com"
        , BigDecimal.valueOf(20));
Course course = new Course("Java-Advanced","OOP,Design Patterns, Data structures",27);

        entityManager.persist(student);
        entityManager.persist(student2);
        entityManager.persist(teacher);
        entityManager.persist(course);


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
