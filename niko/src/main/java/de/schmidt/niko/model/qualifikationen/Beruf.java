package de.schmidt.niko.model.qualifikationen;

import de.schmidt.niko.model.enums.Position;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Embeddable
public class Beruf {

    @Column(name="BERUFSTITEL")
    private String berufsBezeichnung;

    @Enumerated(value= EnumType.STRING)
    @Column(name="POSITION")
    private Position position;

    @Column(name="ANGESTELLTSEIT")
    private LocalDate angestelltSeit;

    @Column(name="ANGESTELLTBIS")
    private LocalDate angestelltBis;

    public Beruf() {
    }

    public Beruf(String berufsBezeichnung, LocalDate angestelltSeit) {
        this.berufsBezeichnung = berufsBezeichnung;
        this.angestelltSeit = angestelltSeit;
    }

    public Beruf(String berufsBezeichnung, LocalDate angestelltSeit, LocalDate angestelltBis) {
        this.berufsBezeichnung = berufsBezeichnung;
        this.angestelltSeit = angestelltSeit;
        this.angestelltBis = angestelltBis;
    }

    public Beruf(String berufsBezeichnung, Position position, LocalDate angestelltSeit) {
        this.berufsBezeichnung = berufsBezeichnung;
        this.position = position;
        this.angestelltSeit = angestelltSeit;
        this.angestelltBis = null;
    }

    public Beruf(String berufsBezeichnung, Position position, LocalDate angestelltSeit, LocalDate angestelltBis) {
        this.berufsBezeichnung = berufsBezeichnung;
        this.position = position;
        this.angestelltSeit = angestelltSeit;
        this.angestelltBis = angestelltBis;
    }

    public String getBerufsBezeichnung() {
        return berufsBezeichnung;
    }

    public void setBerufsBezeichnung(String berufsBezeichnung) {
        this.berufsBezeichnung = berufsBezeichnung;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getAngestelltSeit() {
        return angestelltSeit;
    }

    public void setAngestelltSeit(LocalDate angestelltSeit) {
        this.angestelltSeit = angestelltSeit;
    }

    public LocalDate getAngestelltBis() {
        return angestelltBis;
    }

    public void setAngestelltBis(LocalDate angestelltBis) {
        this.angestelltBis = angestelltBis;
    }

    @Override
    public String toString() {
        return "Beruf{" +
                "titel='" + berufsBezeichnung + '\'' +
                ", position=" + position +
                ", angestelltSeit=" + angestelltSeit +
                ", angestelltBis=" + angestelltBis +
                '}';
    }
}
