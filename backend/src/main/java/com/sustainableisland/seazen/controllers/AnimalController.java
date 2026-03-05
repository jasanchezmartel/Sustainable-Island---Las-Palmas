package com.sustainableisland.seazen.controllers;

import com.sustainableisland.seazen.models.Animal;
import com.sustainableisland.seazen.repositories.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    }

    @GetMapping("/user/{userId}")
    public List<Animal> getAnimalsByUserId(@PathVariable Long userId) {
        return animalRepository.findByUserId(userId);
    }

    @PostMapping
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalRepository.save(animal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable Long id, @RequestBody Animal animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(animalDetails.getName());
            animal.setType(animalDetails.getType());
            animal.setAnimalStatus(animalDetails.getAnimalStatus());
            return ResponseEntity.ok(animalRepository.save(animal));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        return animalRepository.findById(id).map(animal -> {
            animalRepository.delete(animal);
            return ResponseEntity.ok().<Void>build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
