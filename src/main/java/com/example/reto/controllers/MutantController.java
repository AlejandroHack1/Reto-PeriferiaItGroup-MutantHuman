package com.example.reto.controllers;

import com.example.reto.models.Mutant;
import com.example.reto.models.Stats;
import com.example.reto.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@RestController
public class MutantController {

    @Autowired
    private MutantService service;

    @GetMapping("/stats")
    public ResponseEntity<?> detalle() {
        Optional<Stats> usuarioOptional = service.getStats();
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/mutant")
    public ResponseEntity<?> crear(@Valid @RequestBody Mutant mutant, BindingResult result) {

        Stats stats = new Stats();

        if(result.hasErrors()){
            return validar(result);
        }


        if(mutant.getDna().length != 0 && service.porDna(mutant.getDna()).isPresent()){
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Ya está registrado ese ADN"));
        }

        //verifica que contenga las letras del nucleotido
        if(checkArrayNucleotide(mutant.getDna())){

            stats.setId(1L);

            //si detecta que es mutante
            if(isMutant(mutant.getDna())){
                if(service.checkCountTable() == 0){
                    stats.setCount_mutant_dna(1);
                    stats.setCount_human_dna(0);
                    stats.setRatio(0);
                } else {
                    stats.setCount_mutant_dna(service.getCount_mutant_dna() + 1);
                    stats.setCount_human_dna(service.getCount_human_dna());

                    //calcular ratio
                    if(service.getCount_human_dna() > 0){
                        float mutante = service.getCount_mutant_dna() + 1;
                        float human = service.getCount_human_dna();
                        stats.setRatio(mutante/human);

                        //System.out.println(mutante / human);

                    }
                }

                stats.setId(1L);
                service.guardarstats(stats);
                return ResponseEntity.status(HttpStatus.OK).body(service.guardar(mutant));
            }
            else{

                if(service.checkCountTable() == 0){
                    stats.setCount_human_dna(1);
                    stats.setCount_mutant_dna(0);
                    stats.setRatio(0);

                }else{
                    stats.setCount_human_dna(service.getCount_human_dna() + 1);
                    stats.setCount_mutant_dna(service.getCount_mutant_dna());

                    //calcular ratio
                    if(service.getCount_mutant_dna() > 0){
                        float mutante = service.getCount_mutant_dna();
                        float human = service.getCount_human_dna() + 1;
                        stats.setRatio(mutante/human);
                    }
                }
                stats.setId(1L);
                service.guardarstats(stats);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(service.guardar(mutant));
            }

        }


        return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje","Array no contiene nucleotidos correctos"));

    }

    private ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    //verifica letras correctas de los nucleotides
    public static boolean checkArrayNucleotide(String[] dna) {

        for (String dna1 : dna) {
            String nucleotideRegex = "^[ATGC]+$";
            if (!Pattern.matches(nucleotideRegex, dna1)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isMutant(String[] dna) {
        int size = dna.length;
        int count = 0;

        // Verificación Horizontal
        for (String dna1 : dna) {
            for (int j = 0; j < dna1.length() - 3; j++) {
                if (dna1.charAt(j) == dna1.charAt(j + 1)
                        && dna1.charAt(j) == dna1.charAt(j + 2)
                        && dna1.charAt(j) == dna1.charAt(j + 3)) {
                    count++;
                }
            }
        }

        // Verificar verticales
        for (int i = 0; i < size - 3; i++) {
            for (int j = 0; j < size; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j)
                        && dna[i].charAt(j) == dna[i + 2].charAt(j)
                        && dna[i].charAt(j) == dna[i + 3].charAt(j)) {
                    count++;
                }
            }
        }

        // Verificar diagonales (izquierda a derecha)
        for (int i = 0; i < size - 3; i++) {
            for (int j = 0; j < size - 3; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j + 1)
                        && dna[i].charAt(j) == dna[i + 2].charAt(j + 2)
                        && dna[i].charAt(j) == dna[i + 3].charAt(j + 3)) {
                    count++;
                }
            }
        }

        // Verificar diagonales (derecha a izquierda)
        for (int i = 0; i < size - 3; i++) {
            for (int j = 3; j < size; j++) {
                if (dna[i].charAt(j) == dna[i + 1].charAt(j - 1)
                        && dna[i].charAt(j) == dna[i + 2].charAt(j - 2)
                        && dna[i].charAt(j) == dna[i + 3].charAt(j - 3)) {
                    count++;
                }
            }
        }

        return count > 1;
    }

}
