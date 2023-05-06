package com.example.reto.repositories;

import com.example.reto.models.Mutant;
import com.example.reto.models.Stats;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatsRepository extends CrudRepository<Stats, Long> {

    @Query("select s.count_mutant_dna from Stats s")
    int getCount_mutant_dna();

    @Query("select s.count_human_dna from Stats s")
    int getCount_human_dna();

    @Query("select count(*) from Stats")
    int checkCountTable();

}
