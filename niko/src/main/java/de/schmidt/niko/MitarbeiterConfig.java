package de.schmidt.niko;

import de.schmidt.niko.model.enums.Geschlecht;
import de.schmidt.niko.model.enums.Position;
import de.schmidt.niko.model.enums.Status;
import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;
import de.schmidt.niko.model.mitarbeiter.Person;
import de.schmidt.niko.model.qualifikationen.Ausbildung;
import de.schmidt.niko.model.qualifikationen.Beruf;
import de.schmidt.niko.model.qualifikationen.Skill;
import de.schmidt.niko.repos.MitarbeiterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Generates some test data on startup
 */

@Configuration
public class MitarbeiterConfig {

    @Bean
    CommandLineRunner commandLineRunner(MitarbeiterRepository mitarbeiterRepository){
        return args -> {

            Person p1 = new Person(
                    "schmidt",
                    "niko",
                    Geschlecht.MÄNNLICH,
                    LocalDate.of(1984, Month.DECEMBER, 8));

            Person p2 = new Person(
                    "schmidt",
                    "mirko",
                    Geschlecht.MÄNNLICH,
                    LocalDate.of(1986, Month.FEBRUARY, 18));
            Person p3 = new Person(
                    "schmidt",
                    "dirko",
                    Geschlecht.NON_BINARY,
                    LocalDate.of(1988, Month.JANUARY, 10));

            Set<Skill> skills = new HashSet<>();
            Set<Skill> skills1 = new HashSet<>();

            Skill skill1 = new Skill("Java", 8);
            Skill skill2 = new Skill("C#", 7);
            Skill skill3 = new Skill("OOP", 8);
            Skill skill4 = new Skill("Java", 5);
            Skill skill5 = new Skill("C#", 6);
            Skill skill6 = new Skill("OOP", 4);

            skills.add(skill1);
            skills.add(skill2);
            skills.add(skill3);
            skills1.add(skill4);
            skills1.add(skill5);
            skills1.add(skill6);

            Beruf beruf = new Beruf(
                    "Programmierer",
                    Position.JUNIOR,
                    LocalDate.of(2020,3,1));

            Beruf beruf1 = new Beruf(
                    "Softwareentwickler",
                    Position.ASSOCIATE,
                    LocalDate.of(2018,8,1));

            Beruf beruf2 = new Beruf(
                    "Softwarearchitekt",
                    Position.SENIOR,
                    LocalDate.of(2015,1,1));

            Set<Ausbildung> ausbildungen = new HashSet<>();

            Ausbildung ausbildung = new Ausbildung(
                    "Abitur",
                    "Fritz-Erler Gymnasium",
                    Status.BESTANDEN,
                    LocalDate.of(1999,9,1),
                    LocalDate.of(2005,9,1),
                    1.7f
                    );
            ausbildungen.add(ausbildung);

            Ausbildung ausbildung1 = new Ausbildung(
                    "Bachelor",
                    "Universität des Saarlandes",
                    Status.BESTANDEN,
                    LocalDate.of(1999,9,1),
                    LocalDate.of(2005,9,1),
                    2.7f
            );
            ausbildungen.add(ausbildung1);

            Ausbildung ausbildung2 = new Ausbildung(
                    "Weiterbildung IT",
                    "karriereTutor",
                    Status.BESTANDEN,
                    LocalDate.of(1999,9,1),
                    LocalDate.of(2005,9,1),
                    1.0f
            );
            ausbildungen.add(ausbildung2);

            Mitarbeiter m1 = new Mitarbeiter(p1, ausbildungen, beruf, skills, "niko@schmidt.de");
            Mitarbeiter m2 = new Mitarbeiter(p2, ausbildungen, beruf1, skills1, "mirko@schmidt.de");
            Mitarbeiter m3 = new Mitarbeiter(p3, ausbildungen, beruf2, skills1, "dirko@schmidt.de");
            mitarbeiterRepository.saveAll(List.of(m1,m2,m3));
            //TODO: foreign key mitarbeiter id wird null/nicht richtig gesetzt in ausbildungen und skills tables
        };
    }
}
