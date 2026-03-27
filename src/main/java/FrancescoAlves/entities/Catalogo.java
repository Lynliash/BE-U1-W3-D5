package FrancescoAlves.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "catalogo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_elemento", discriminatorType = DiscriminatorType.STRING)
public abstract class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @Column(name = "titolo", nullable = false)
    private String titolo;

    @Column(name = "anno_di_pubblicazione", nullable = false)
    private int annoDiPubblicazione;

    @Column(name = "numero_pagine", nullable = false)
    private int numeroPagine;

    public Catalogo() {
    }

    public Catalogo(int numeroPagine, int annoDiPubblicazione, String titolo, String isbn) {
        this.numeroPagine = numeroPagine;
        this.annoDiPubblicazione = annoDiPubblicazione;
        this.titolo = titolo;
        this.isbn = isbn;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    public void setNumeroPagine(int numeroPagine) {
        this.numeroPagine = numeroPagine;
    }

    public int getAnnoDiPubblicazione() {
        return annoDiPubblicazione;
    }

    public void setAnnoDiPubblicazione(int annoDiPubblicazione) {
        this.annoDiPubblicazione = annoDiPubblicazione;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Catalogo: " + id + " titolo: " + titolo + " con numero di pagine: " + numeroPagine + " pubblicato il: " + annoDiPubblicazione;
    }
}
