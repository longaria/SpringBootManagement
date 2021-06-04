package de.schmidt.niko.model.mitarbeiter;

import de.schmidt.niko.model.enums.Geschlecht;
import de.schmidt.niko.model.qualifikationen.Ausbildung;
import de.schmidt.niko.model.qualifikationen.Beruf;
import de.schmidt.niko.model.qualifikationen.Skill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity(name="Mitarbeiter")
@DiscriminatorValue("Mitarbeiter")
public class Mitarbeiter extends Person {

    @Column(name = "BERUF")
    @Embedded
    private Beruf beruf;

    @OneToMany(mappedBy = "mitarbeiter", cascade = CascadeType.ALL)
    private Set<Ausbildung> ausbildungen = new HashSet<>();

    @OneToMany(mappedBy = "mitarbeiter", cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    @Column(name = "EMAIL")
    private String emailBeruf;

    public Mitarbeiter() {
    }

    public Mitarbeiter(String name,
                       String vorname,
                       Geschlecht geschlecht,
                       LocalDate geburtsDatum,
                       Set<Ausbildung> ausbildungen,
                       Beruf beruf,
                       Set<Skill> skills,
                       String emailBeruf) {

        super(name, vorname, geschlecht, geburtsDatum);
        this.ausbildungen = ausbildungen;
        this.beruf = beruf;
        this.skills = skills;
        this.emailBeruf = emailBeruf;
    }

    public Mitarbeiter(Person p,
                       Set<Ausbildung> ausbildungen,
                       Beruf beruf,
                       Set<Skill> skills,
                       String emailBeruf) {

        super(p.getName(), p.getVorname(), p.getGeschlecht(), p.getGeburtsdatum());
        this.ausbildungen = ausbildungen;
        this.beruf = beruf;
        this.skills = skills;
        this.emailBeruf = emailBeruf;
    }

    public String getEmailBeruf() {
        return emailBeruf;
    }

    public void setEmailBeruf(String emailBeruf) {
        this.emailBeruf = emailBeruf;
    }

    public Set<Ausbildung> getAusbildungen() {
        return ausbildungen;
    }

    public void setAusbildungen(Set<Ausbildung> ausbildungen) {
        this.ausbildungen = ausbildungen;
    }

    public Beruf getBeruf() {
        return beruf;
    }

    public void setBeruf(Beruf beruf) {
        this.beruf = beruf;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Mitarbeiter{" +
                "ausbildungen=" + ausbildungen +
                ", beruf=" + beruf +
                ", skills=" + skills +
                ", emailBeruf='" + emailBeruf + '\'' +
                '}';
    }
}
