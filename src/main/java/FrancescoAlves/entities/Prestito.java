package FrancescoAlves.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestito")
@NamedQueries({
        @NamedQuery(
                name = "Prestito.elementiAttualmenteInPrestito",
                query = "SELECT p.catalogo FROM Prestito p WHERE p.utente.numeroDiTessera = :tessera AND p.dataRestituzioneEffettiva IS NULL"
        ),

        @NamedQuery(
                name = "Prestito.prestitiScaduti",
                query = "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :oggi AND p.dataRestituzioneEffettiva IS NULL"
        )
})

public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_inizio_prestito", nullable = false)
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituizione_prevista", nullable = false)
    private LocalDate dataRestituzionePrevista;

    @Column(name = "data_restituzione_effettiva")
    private LocalDate dataRestituzioneEffettiva;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "catalogo_id", nullable = false)
    private Catalogo catalogo;

    public Prestito() {
    }

    public Prestito(Catalogo catalogo, Utente utente, LocalDate dataInizioPrestito) {
        this.catalogo = catalogo;
        this.utente = utente;
        this.dataInizioPrestito = dataInizioPrestito;
    }

    //    viene triggerata in automatico prima di fare INSERT
    @PrePersist
    private void calcolaDataPrevista() {
        if (dataInizioPrestito != null) {
            dataRestituzionePrevista = dataInizioPrestito.plusDays(30);
        }
    }


    public Long getId() {
        return id;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevista() {
        return dataRestituzionePrevista;
    }

    public void setDataRestituzionePrevista(LocalDate dataRestituzionePrevista) {
        this.dataRestituzionePrevista = dataRestituzionePrevista;
    }

    public LocalDate getDataRestituzioneEffettiva() {
        return dataRestituzioneEffettiva;
    }

    public void setDataRestituzioneEffettiva(LocalDate dataRestituzioneEffettiva) {
        this.dataRestituzioneEffettiva = dataRestituzioneEffettiva;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }


    @Override
    public String toString() {
        return "Prestito numero: " + id + " inziato il: " + dataInizioPrestito + " termina il:" + dataRestituzionePrevista + " restiuto il: " + dataRestituzioneEffettiva;
    }
}
