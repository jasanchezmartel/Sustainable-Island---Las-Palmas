package com.sustainableisland.seazen.controllers;

import com.sustainableisland.seazen.dtos.AnimalDTO;
import com.sustainableisland.seazen.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping
    public List<AnimalDTO> getAllAnimals() {
        return animalService.getAllAnimals();
    }

    @GetMapping("/user/{userId}")
    public List<AnimalDTO> getAnimalsByUserId(@PathVariable Long userId) {
        return animalService.getAnimalsByUserId(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable Long id) {
        return animalService.getAnimalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AnimalDTO createAnimal(@RequestBody AnimalDTO animalDTO) {
        return animalService.createAnimal(animalDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDTO> updateAnimal(@PathVariable Long id, @RequestBody AnimalDTO animalDetails) {
        return animalService.updateAnimal(id, animalDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable Long id) {
        return animalService.deleteAnimal(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
