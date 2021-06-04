package de.schmidt.niko.model.qualifikationen;

import de.schmidt.niko.model.enums.Status;
import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Ausbildungen")
public class Ausbildung implements Serializable {

    @Id
    @SequenceGenerator(
            name = "ausbildung_sequence",
            sequenceName = "ausbildung_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ausbildung_sequence"
    )
    private int ausbildungid;

    //TODO: ManyToOne weil mitarbeiter OneToMany, aber eigentlich ManyToMany notwendig
    @ManyToOne
    @JoinColumn(name="FK_MITARBEITER")
    private Mitarbeiter mitarbeiter;

    @Column(name="ABSCHLUSS")
    private String abschluss;

    @Column(name="INSTITUTION")
    private String institution;

    @Enumerated(value = EnumType.STRING)
    @Column(name="STATUS")
    private Status status;

    @Column(name="BEGINN")
    private LocalDate beginn;

    @Column(name="ENDE")
    private LocalDate ende;

    @Column(name="NOTE")
    private Float note;

    public Ausbildung() {
    }

    public Ausbildung(String abschluss, String institution, Status status, LocalDate beginn, LocalDate ende, Float note) {
        this.abschluss = abschluss;
        this.institution = institution;
        this.status = status;
        this.beginn = beginn;
        this.ende = ende;
        this.note = note;
    }

    public Ausbildung(String abschluss, String institution, Status status, LocalDate beginn) {
        this.abschluss = abschluss;
        this.institution = institution;
        this.status = status;
        this.beginn = beginn;
    }

    public int getAusbildungid() {
        return ausbildungid;
    }

    public void setAusbildungid(int ausbildungid) {
        this.ausbildungid = ausbildungid;
    }

    public Mitarbeiter getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getAbschluss() {
        return abschluss;
    }

    public void setAbschluss(String abschluss) {
        this.abschluss = abschluss;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getBeginn() {
        return beginn;
    }

    public void setBeginn(LocalDate beginn) {
        this.beginn = beginn;
    }

    public LocalDate getEnde() {
        return ende;
    }

    public void setEnde(LocalDate ende) {
        this.ende = ende;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Ausbildung{" +
                "abschluss='" + abschluss + '\'' +
                ", institution='" + institution + '\'' +
                ", status=" + status +
                ", beginn=" + beginn +
                ", ende=" + ende +
                ", note=" + note +
                '}';
    }
}
