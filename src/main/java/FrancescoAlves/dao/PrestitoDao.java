package FrancescoAlves.dao;

import FrancescoAlves.entities.Catalogo;
import FrancescoAlves.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {
    private final EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        em.persist(prestito);

        entityTransaction.commit();
        System.out.println("elemento creato correttamente");
    }


    public List<Catalogo> getCataloghiAttualmenteInPrestito(String tessera) {
        return em.createNamedQuery("Prestito.elementiAttualmenteInPrestito", Catalogo.class)
                .setParameter("tessera", tessera).getResultList();
    }


    public List<Prestito> getPrestitiScadutiAndNonRestituiti() {
        return em.createNamedQuery("Prestito.prestitiScaduti", Prestito.class)
                .setParameter("oggi", LocalDate.now()).getResultList();
    }


}
