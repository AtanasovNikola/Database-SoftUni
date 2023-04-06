package introductionToHibernate;

import introductionToHibernate.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class P05AddingANewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        em.getTransaction().begin();
String addressName = "Vitoshka 15";
        String lastName = scanner.nextLine();
        Address address = new Address();
        address.setText(addressName);
        em.persist(address);
        em.createQuery("UPDATE Employee e SET e.address = :address WHERE e.lastName = :name")
                        .setParameter("address", address)
                                .setParameter("name",lastName)
                                        .executeUpdate();


        em.getTransaction().commit();
        em.close();
    }
}
