package com.example.reto.models;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.persistence.*;

@Entity
@Table(name="mutant")
public class Mutant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String[] dna;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}
