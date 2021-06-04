package de.schmidt.niko.api;

import de.schmidt.niko.model.enums.Geschlecht;
import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;
import de.schmidt.niko.model.qualifikationen.Ausbildung;
import de.schmidt.niko.model.qualifikationen.Beruf;
import de.schmidt.niko.model.qualifikationen.Skill;
import de.schmidt.niko.service.MitarbeiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="mitarbeiter")
public class MitarbeiterController {

    private final MitarbeiterService mitarbeiterService;

    @Autowired
    public MitarbeiterController(MitarbeiterService mitarbeiterService) {
        this.mitarbeiterService = mitarbeiterService;
    }

    @GetMapping
    public List<Mitarbeiter> getAlleMitarbeiter(){
        return mitarbeiterService.getAlleMitarbeiter();
    }

    @GetMapping(path = "{term}")
    public List<Mitarbeiter> getMitarbeiter(@PathVariable("term") String searchTerm){
        return mitarbeiterService.getMitarbeiter(searchTerm);
    }

    @PostMapping
    public void registerMitarbeiter(@RequestBody Mitarbeiter mitarbeiter){
        mitarbeiterService.addNewPerson(mitarbeiter);
    }

    @DeleteMapping(path = "{personID}")
    public void deleteMitarbeiter(@PathVariable("personID") Long personID){
        mitarbeiterService.deletePerson(personID);
    }

    @PutMapping(path = "{personID}")
    public void updateMitarbeiter(@PathVariable("personID") Long id,
                                  @RequestParam(required = false) String name,
                                  @RequestParam(required = false) String vorname,
                                  @RequestParam(required = false) Geschlecht geschlecht,
                                  @RequestParam(required = false) LocalDate geburtsdatum,
                                  @RequestParam(required = false) Set<Ausbildung> ausbildungen,
                                  @RequestParam(required = false) Set<Skill> skills,
                                  @RequestParam(required = false) Beruf beruf,
                                  @RequestParam(required = false) String email)
    {
        mitarbeiterService.updateMitarbeiter(id,
                name,
                vorname,
                geschlecht,
                geburtsdatum,
                ausbildungen,
                skills,
                beruf,
                email);
    }
}
