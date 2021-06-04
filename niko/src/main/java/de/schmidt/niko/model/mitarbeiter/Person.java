package de.schmidt.niko.model.mitarbeiter;

import de.schmidt.niko.model.enums.Geschlecht;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity(name="Person_SINGLE_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("PERSON")
@Table(name = "Personen")
public class Person {

    @Id
    @Column(nullable = false)
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    private Long personID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VORNAME")
    private String vorname;

    @Column(name = "GESCHLECHT")
    @Enumerated(value = EnumType.STRING)
    private Geschlecht geschlecht;

    @Column(name = "GEBURTSDATUM")
    private LocalDate geburtsdatum;

    @Transient
    private Integer alter;

    public Person() {
    }

    public Person(String name, String vorname, Geschlecht geschlecht, LocalDate geburtsdatum) {
        this.name = name;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
    }

    public Person(Long personID, String name, String vorname, Geschlecht geschlecht, LocalDate geburtsdatum) {
        this.personID = personID;
        this.name = name;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
    }

    public Geschlecht getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(Geschlecht geschlecht) {
        this.geschlecht = geschlecht;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public Long getPersonID() {
        return personID;
    }

    public void setPersonID(Long personID) {
        this.personID = personID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Integer getAlter() {
        return Period.between(this.geburtsdatum, LocalDate.now()).getYears();
    }

    public void setAlter(int alter) {
        this.alter = alter;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID=" + personID +
                ", name='" + name + '\'' +
                ", vorname='" + vorname + '\'' +
                ", geschlecht=" + geschlecht +
                ", geburtsdatum=" + geburtsdatum +
                ", alter=" + alter +
                '}';
    }
}
