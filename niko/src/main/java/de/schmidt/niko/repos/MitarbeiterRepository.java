package de.schmidt.niko.repos;

import de.schmidt.niko.model.mitarbeiter.Mitarbeiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MitarbeiterRepository extends JpaRepository<Mitarbeiter, Long>, JpaSpecificationExecutor<Mitarbeiter> {

    @Query("SELECT p FROM Mitarbeiter p WHERE p.name = ?1 and p.vorname = ?2 and p.geburtsdatum = ?3")
    List<Mitarbeiter> findByNameAndVornameAndGeburtsdatum(String name, String vorname, LocalDate geburtsdatum);
}
