package com.example.reto.repositories;

import com.example.reto.models.Mutant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MutantRepository extends CrudRepository<Mutant, Long> {

    @Query("select m from Mutant m where m.dna=?1")
    Optional<Mutant> porDna(String[] dna);
}
