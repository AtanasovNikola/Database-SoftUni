package _02_;

import _02_.entities.Customer;
import _02_.entities.Product;
import _02_.entities.Sale;
import _02_.entities.StoreLocation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer();
        customer.setEmail("atanasov_n@mail.ru");
        customer.setName("Nikola Atanasov");
        customer.setCreditCardNumber("ABCD123456");

        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setLocationName("Plovdiv");

        Product product = new Product();
        product.setName("product");
        product.setPrice(BigDecimal.valueOf(9.99));
        product.setQuantity(1);

        Sale sale = new Sale();
        sale.setCustomer(customer);
        sale.setProduct(product);
        sale.setStoreLocation(storeLocation);
        sale.setDateTime(LocalDateTime.now());


        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("sales");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            entityManager.persist(customer);
            entityManager.persist(product);
            entityManager.persist(storeLocation);
            entityManager.persist(sale);

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
