package FrancescoAlves.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cognome;

    @Column(name = "data_di_nascita", nullable = false)
    private LocalDate dataDiNascita;

    @Column(name = "numero_di_tessera", nullable = false, unique = true)
    private String numeroDiTessera;


    public Utente() {
    }

    public Utente(String nome, String cognome, LocalDate dataDiNascita, String numeroDiTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.numeroDiTessera = numeroDiTessera;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getNumeroDiTessera() {
        return numeroDiTessera;
    }

    public void setNumeroDiTessera(String numeroDiTessera) {
        this.numeroDiTessera = numeroDiTessera;
    }


    @Override
    public String toString() {
        return "id utente: " + id + " numero tessera: " + numeroDiTessera;
    }
}
