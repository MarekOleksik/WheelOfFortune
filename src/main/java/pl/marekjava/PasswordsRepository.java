package pl.marekjava;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PasswordsRepository {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em = factory.createEntityManager();

    public void createNewPassword(String password) {
        Passwords newPassword = new Passwords(password);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(newPassword);
        System.out.println("Hasło zapisane w bazie danych");
        transaction.commit();
    }

    public Passwords findById(long id) {
        em.clear();
        return em.find(Passwords.class, id);
    }
}
