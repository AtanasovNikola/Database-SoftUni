package _05_;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CodeFirstEx");
        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();
        User user = new User("Nikola", "Atanasov", "atanasov_n@mail.ru", "Gishmi11!");
        User user2 = new User("Atanas", "Atanasov", "atanasov_a@mail.bg", "qwer!");
        User user3 = new User("Galya", "Atanasova", "atanasova_g@abv.bg", "asdafd!");
        entityManager.persist(user);
        entityManager.persist(user2);
        entityManager.persist(user3);
        CreditCard billingDetail = new CreditCard(user.getId(), "Visa", LocalDate.now().getMonth(), LocalDate.now().getYear());
        user.setCreditCard(billingDetail);
        CreditCard billingDetail2 = new CreditCard(user2.getId(), "MasterCard", LocalDate.now().getMonth(), LocalDate.now().getYear());
        BankAccount billingDetail3 = new BankAccount(user3.getId(), "UniCredit", "SWIFT034829043");
        entityManager.persist(billingDetail);
        entityManager.persist(billingDetail2);
        entityManager.persist(billingDetail3);
        entityManager.getTransaction().commit();

        User found1 = entityManager.find(User.class, 1);
        BillingDetail found2 = entityManager.find(CreditCard.class, 1);
        BillingDetail found3 = entityManager.find(CreditCard.class, 2);

        System.out.println(found1.getFirstName());
        System.out.println(found2.getOwner());
        System.out.println(found3.getId());

        entityManager.close();
    }
}
