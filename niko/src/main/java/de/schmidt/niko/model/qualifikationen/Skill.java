package de.schmidt.niko.model.qualifikationen;

import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;

import javax.persistence.*;

@Entity
@Table(name="Skills")
public class Skill {

    @Id
    @SequenceGenerator(
            name = "skill_sequence",
            sequenceName = "skill_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skill_sequence"
    )
    private int skillid;

    @ManyToOne
    @JoinColumn(name="FK_MITARBEITER")
    private Mitarbeiter mitarbeiter;

    @Column(name="SKILL")
    private String skill;

    @Column(name="KOMPETENZ")
    private Integer kompetenz;

    public Skill() {
    }

    public Skill(String name, Integer kompetenz) {
        this.skill = name;
        this.kompetenz = kompetenz;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getKompetenz() {
        return kompetenz;
    }

    public void setKompetenz(Integer kompetenz) {
        this.kompetenz = kompetenz;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + skill + '\'' +
                ", kompetenz=" + kompetenz +
                '}';
    }
}
