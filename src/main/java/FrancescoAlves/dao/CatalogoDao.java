package FrancescoAlves.dao;

import FrancescoAlves.entities.Catalogo;
import FrancescoAlves.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CatalogoDao {
    private final EntityManager em;


    public CatalogoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Catalogo catalogo) {
        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        em.persist(catalogo);

        entityTransaction.commit();
        System.out.println("elemento creato correttamente");
    }


    public Catalogo ricercaPerISBN(String isbn) {
        Catalogo trovato = em.createQuery("SELECT c FROM Catalogo c WHERE c.isbn = :isbn", Catalogo.class).setParameter("isbn", isbn)
                .getSingleResult();
        return trovato;
    }


    public void rimuoviElementoByISBN(String isbn) {

        Catalogo trovato = ricercaPerISBN(isbn);

        EntityTransaction entityTransaction = em.getTransaction();

        entityTransaction.begin();

        em.remove(trovato);

        entityTransaction.commit();
    }


    public List<Catalogo> ricercaPerAnno(int anno) {

        List<Catalogo> trovato = em.createQuery("SELECT a FROM Catalogo a WHERE a.annoDiPubblicazione = :anno", Catalogo.class).setParameter("anno", anno)
                .getResultList();
        return trovato;
    }


    public List<Libro> ricercaPerAutore(String autore) {
        return em.createQuery("SELECT l FROM Libro WHERE l.autore = :autore", Libro.class).setParameter("autore", autore).getResultList();
    }


    public List<Catalogo> ricercaPerTitolo(String parteTitolo) {
        return em.createQuery("SELECT t FROM Catalogo WHERE t.titolo LIKE CONCAT('%',:parteTitolo,'%')", Catalogo.class).setParameter("parteTitolo", parteTitolo).getResultList();
    }


}
