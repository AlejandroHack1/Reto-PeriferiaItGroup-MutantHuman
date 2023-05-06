package com.example.reto.services;

import com.example.reto.models.Mutant;
import com.example.reto.models.Stats;
import com.example.reto.repositories.MutantRepository;
import com.example.reto.repositories.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MutantServiceImpl implements  MutantService{

    @Autowired
    private MutantRepository repository;

    @Autowired
    private StatsRepository statsrepository;

    @Override
    public Mutant guardar(Mutant mutant) {
        return repository.save(mutant);
    }

    @Override
    public Optional<Mutant> porDna(String[] dna) {
        return repository.porDna(dna);
    }

    @Override
    public Stats guardarstats(Stats stats) {
        return statsrepository.save(stats);
    }

    @Override
    public Optional<Stats> getStats() {
        return statsrepository.findById(1L);
    }

    @Override
    public int getCount_mutant_dna() {
        return statsrepository.getCount_mutant_dna();
    }

    @Override
    public int getCount_human_dna() {
        return statsrepository.getCount_human_dna();
    }

    @Override
    public int checkCountTable() {
        return statsrepository.checkCountTable();
    }



}
