package FrancescoAlves;

import FrancescoAlves.dao.CatalogoDao;
import FrancescoAlves.dao.PrestitoDao;
import FrancescoAlves.dao.UtenteDao;
import FrancescoAlves.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Application {

    public static void main(String[] args) {


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogo_bibliografico");

        EntityManager em = emf.createEntityManager();


        CatalogoDao catalogoDao = new CatalogoDao(em);
        PrestitoDao prestitoDao = new PrestitoDao(em);
        UtenteDao utenteDao = new UtenteDao(em);

//       Aggiunta al catalogo

        Libro libro1 = new Libro(20, 2010, "harry", "1-1-1", "Mario", "Horror");
        Libro libro2 = new Libro(100, 1990, "Lotr", "2-2-2", "Luigi", "Fantasy");
        Libro libro3 = new Libro(560, 2060, "Eragorn", "3-3-3", "Ugo", "Drama");

        Rivista rivista1 = new Rivista(116, 1967, "WOW", "5-5-5", Periodicita.MENSILE);
        Rivista rivista2 = new Rivista(290, 1990, "Cenere", "6-6-6", Periodicita.SEMESTRALE);
        Rivista rivista3 = new Rivista(340, 2021, "Prova", "7-7-7", Periodicita.SETTIMANALE);

        catalogoDao.save(libro1);
        catalogoDao.save(libro2);
        catalogoDao.save(libro3);
        catalogoDao.save(rivista1);
        catalogoDao.save(rivista2);
        catalogoDao.save(rivista3);
        System.out.println("Elementi aggiunti al catalogo");


//        aggiunta utenti

        Utente utente1 = new Utente("paolo", "rossi", LocalDate.of(1990, 12, 3), "tessera1");
        Utente utente2 = new Utente("matteo", "verde", LocalDate.of(1980, 12, 15), "tessera2");

        utenteDao.save(utente1);
        utenteDao.save(utente2);
        System.out.println("Utenti aggiunti al catalogo");


//        Catalogo libroHarry = catalogoDao.ricercaPerISBN("1-1-1");
//        Catalogo libroLotr = catalogoDao.ricercaPerISBN("2-2-2");
//        Catalogo libroEragorn = catalogoDao.ricercaPerISBN("3-3-3");
//

        System.out.println(libro1);
        System.out.println(utente1);


//        creazione prestiti
        Prestito prestito1 = new Prestito(libro1, utente1, LocalDate.now());
        Prestito prestito2 = new Prestito(libro2, utente2, LocalDate.of(2025, 12, 10));
        prestitoDao.save(prestito1);
        prestitoDao.save(prestito2);
        System.out.println("prestiti salvati");
//      presititi salvati


//        ricerca per isbn
        try {
            Catalogo ricerca = catalogoDao.ricercaPerISBN("2-2-2");
            System.out.println("elemento trovato");
        } catch (NoResultException ex) {
            System.out.println("elemento non trovato");
        }

//        ricerca per anno

        List<Catalogo> ricercaPerAnno = catalogoDao.ricercaPerAnno(1990);
        if (!ricercaPerAnno.isEmpty()) {
            ricercaPerAnno.forEach(System.out::println);
        } else {
            System.out.println("nessun elemento trovato per quell'anno");
        }

//        ricerca per autore

        List<Libro> ricercaPerAutore = catalogoDao.ricercaPerAutore("Mario");
        if (!ricercaPerAutore.isEmpty()) {
            ricercaPerAutore.forEach(System.out::println);
        } else {
            System.out.println("nessun elemento trovato per quell'autore");
        }


//        ricerca per titolo

        List<Catalogo> ricercaPerTitolo = catalogoDao.ricercaPerTitolo("Lotr");
        if (!ricercaPerTitolo.isEmpty()) {
            ricercaPerTitolo.forEach(System.out::println);
        } else {
            System.out.println("nessun elemento trovato per quel titolo");
        }


//        elementi in prestito


        List<Catalogo> elementiInPrestito = prestitoDao.getCataloghiAttualmenteInPrestito("tessera1");
        if (!elementiInPrestito.isEmpty()) {
            elementiInPrestito.forEach(System.out::println);
        } else {
            System.out.println("nessun elemento trovato");
        }


//        ricerca prestiti scaduti


        List<Prestito> elementiPrestitiScaduti = prestitoDao.getPrestitiScadutiAndNonRestituiti();
        if (!elementiPrestitiScaduti.isEmpty()) {
            elementiPrestitiScaduti.forEach(System.out::println);
        } else {
            System.out.println("nessun elemento trovato");
        }

    }
}
