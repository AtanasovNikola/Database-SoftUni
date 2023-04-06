package introductionToHibernate;

import introductionToHibernate.entities.Town;

import javax.persistence.*;
import java.util.List;

public class P01ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        Query query = em.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> towns = query.getResultList();

        for (Town town : towns) {
            if (town.getName().length() > 5) {
                em.detach(town);
            } else {
                town.setName(town.getName().toUpperCase());
                em.getTransaction().begin();
                em.persist(town);
                em.getTransaction().commit();
            }
        }
        em.close();
        factory.close();
    }
}
