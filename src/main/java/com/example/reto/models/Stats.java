package com.example.reto.models;


import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="stats")
public class Stats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer count_mutant_dna;

    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer count_human_dna;

    @Column(precision=10, scale=2)
    private float ratio;

    public Stats() {
    }

    public Stats(Integer count_mutant_dna, Integer count_human_dna, float ratio) {
        this.count_mutant_dna = count_mutant_dna;
        this.count_human_dna = count_human_dna;
        this.ratio = ratio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCount_mutant_dna() {
        return count_mutant_dna;
    }

    public void setCount_mutant_dna(Integer count_mutant_dna) {
        this.count_mutant_dna = count_mutant_dna;
    }

    public Integer getCount_human_dna() {
        return count_human_dna;
    }

    public void setCount_human_dna(Integer count_human_dna) {
        this.count_human_dna = count_human_dna;
    }

    public float getRatio() {
        return ratio;
    }

    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
