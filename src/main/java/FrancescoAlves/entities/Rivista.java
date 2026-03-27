package FrancescoAlves.entities;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Rivista")
public class Rivista extends Catalogo {

    @Column(name = "periodicita")
    @Enumerated(EnumType.STRING)
    private Periodicita periodicita;

    public Rivista() {
    }

    public Rivista(int numeroPagine, int annoDiPubblicazione, String titolo, String isbn, Periodicita periodicita) {
        super(numeroPagine, annoDiPubblicazione, titolo, isbn);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }


    @Override
    public String toString() {
        return super.toString() + " peridiocità " + periodicita;
    }
}
