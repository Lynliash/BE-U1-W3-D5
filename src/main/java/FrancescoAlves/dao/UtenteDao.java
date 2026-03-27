package FrancescoAlves.dao;


import FrancescoAlves.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDao {

    private final EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        em.persist(utente);

        entityTransaction.commit();
        System.out.println("elemento creato correttamente");
    }

//    public Utente getById(Long id) {
//        Utente trovato = em.find(Utente.class, id);
//        return
//    }
//

}
