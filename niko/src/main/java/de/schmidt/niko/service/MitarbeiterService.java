package de.schmidt.niko.service;

import de.schmidt.niko.model.enums.Geschlecht;
import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;
import de.schmidt.niko.repos.MitarbeiterRepository;
import de.schmidt.niko.model.qualifikationen.Ausbildung;
import de.schmidt.niko.model.qualifikationen.Beruf;
import de.schmidt.niko.model.qualifikationen.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MitarbeiterService {

    private final MitarbeiterRepository mitarbeiterRepository;

    @Autowired
    public MitarbeiterService(MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    public List<Mitarbeiter> getAlleMitarbeiter(){
        return mitarbeiterRepository.findAll();
    }

    public List<Mitarbeiter> getMitarbeiter(String searchTerm){
        //TODO: parse searchTerm into search fields
        return null;// mitarbeiterRepository.findByNameAndVornameAndGeburtsdatum(field1, field2, field3);
    }

    /**
     * adds a new employee to database unless it finds an entry with same last name, first name and date of birth
     * @param mitarbeiter
     */
    public void addNewPerson(Mitarbeiter mitarbeiter) {

        if(mitarbeiter == null) throw new IllegalStateException("Mitarbeiter ist null-Wert");

        List<Mitarbeiter> res = mitarbeiterRepository.findByNameAndVornameAndGeburtsdatum(mitarbeiter.getName(),
                mitarbeiter.getVorname(),
                mitarbeiter.getGeburtsdatum());

        if(!res.isEmpty()) throw new IllegalStateException("Eine Person mit diesem Namen, Vornamen und Geburtsdatum existiert bereits");
        mitarbeiterRepository.save(mitarbeiter);
    }

    /**
     * deletes an employee with given id
     * @param mitarbeiterID
     */
    public void deletePerson(Long mitarbeiterID) {
        if(mitarbeiterRepository.existsById(mitarbeiterID)){
            mitarbeiterRepository.deleteById(mitarbeiterID);
        } else {
            throw new IllegalStateException("Mitarbeiter mit ID " + mitarbeiterID + " existiert nicht.");
        }
    }


    @Transactional
    public void updateMitarbeiter(Long mitarbeiterID,
                                  String name,
                                  String vorname,
                                  Geschlecht geschlecht,
                                  LocalDate geburtsdatum,
                                  Set<Ausbildung> ausbildungen,
                                  Set<Skill> skills,
                                  Beruf beruf,
                                  String email
                                  ) {

        Mitarbeiter mitarbeiter = mitarbeiterRepository.findById(mitarbeiterID).orElseThrow(
                () -> new IllegalStateException("Mitarbeiter mit ID " + mitarbeiterID + " existiert nicht.")
        );

        if(name != null && name.length() > 0 && !Objects.equals(mitarbeiter.getName(), name)){
            mitarbeiter.setName(name);
        }

        if(vorname != null && vorname.length() > 0 && !Objects.equals(mitarbeiter.getVorname(), vorname)){
            mitarbeiter.setVorname(vorname);
        }

        if(geschlecht != null && !Objects.equals(mitarbeiter.getGeschlecht(), geschlecht)){
            boolean vorhanden = false;
            for(Geschlecht g : Geschlecht.values()){
                if(geschlecht == g) vorhanden = true;
            }
            if(vorhanden){
                mitarbeiter.setGeschlecht(geschlecht);
            } else {
                throw new IllegalStateException("Person mit ID " + mitarbeiterID + " hat ungültige Geschlechtsangabe");
            }
        }

        if(geburtsdatum != null && !Objects.equals(mitarbeiter.getGeburtsdatum(), geburtsdatum)){
            int mindestAlter = Period.between(geburtsdatum, LocalDate.now()).getYears();
            if(mindestAlter > 17){
                mitarbeiter.setGeburtsdatum(geburtsdatum);
                mitarbeiter.setAlter(mindestAlter);
            } else {
                throw new IllegalStateException("Person mit ID " + mitarbeiterID + " darf nicht unter 18 Jahre alt sein.");
            }
        }

        if(ausbildungen != null && !Objects.equals(mitarbeiter.getAusbildungen(), ausbildungen)){
            mitarbeiter.setAusbildungen(ausbildungen);
        }

        if(skills != null && !Objects.equals(mitarbeiter.getSkills(), skills)){
            mitarbeiter.setSkills(skills);
        }

        if(beruf != null && !Objects.equals(mitarbeiter.getBeruf(), beruf)){
            mitarbeiter.setBeruf(beruf);
        }

        if(email != null && !Objects.equals(mitarbeiter.getEmailBeruf(), email)){
            //TODO: check that email string has a proper email format
            mitarbeiter.setEmailBeruf(email);
        }
    }
}
