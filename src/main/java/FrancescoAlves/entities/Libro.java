package FrancescoAlves.entities;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Libro")
public class Libro extends Catalogo {
    @Column(name = "autore")
    private String autore;

    @Column(name = "genere")
    private String genere;

    public Libro() {
    }

    public Libro(int numeroPagine, int annoDiPubblicazione, String titolo, String isbn, String autore, String genere) {
        super(numeroPagine, annoDiPubblicazione, titolo, isbn);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }


    @Override
    public String toString() {
        return super.toString() + " scritto da: " + autore;
    }
}
