package com.example.reto.services;

import com.example.reto.models.Mutant;
import com.example.reto.models.Stats;

import java.util.Optional;

public interface MutantService {

    Mutant guardar(Mutant mutant);

    Optional<Mutant> porDna(String[] dna);

    //estadisticas
    Stats guardarstats(Stats stats);
    Optional<Stats> getStats();


    int getCount_mutant_dna();
    int getCount_human_dna();

    int checkCountTable();

}
