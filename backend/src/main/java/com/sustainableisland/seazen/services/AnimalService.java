package com.sustainableisland.seazen.services;

import com.sustainableisland.seazen.dtos.AnimalDTO;
import com.sustainableisland.seazen.models.Animal;
import com.sustainableisland.seazen.models.AnimalStatus;
import com.sustainableisland.seazen.models.User;
import com.sustainableisland.seazen.repositories.AnimalRepository;
import com.sustainableisland.seazen.repositories.AnimalStatusRepository;
import com.sustainableisland.seazen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnimalStatusRepository animalStatusRepository;

    public List<AnimalDTO> getAllAnimals() {
        return animalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<AnimalDTO> getAnimalsByUserId(Long userId) {
        return animalRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AnimalDTO> getAnimalById(Long id) {
        return animalRepository.findById(id).map(this::convertToDTO);
    }

    public AnimalDTO createAnimal(AnimalDTO animalDTO) {
        Animal animal = convertToEntity(animalDTO);
        Animal savedAnimal = animalRepository.save(animal);
        return convertToDTO(savedAnimal);
    }

    public Optional<AnimalDTO> updateAnimal(Long id, AnimalDTO animalDetails) {
        return animalRepository.findById(id).map(animal -> {
            animal.setName(animalDetails.getName());
            animal.setType(animalDetails.getType());
            
            if (animalDetails.getAnimalStatusId() != null) {
                AnimalStatus status = animalStatusRepository.findById(animalDetails.getAnimalStatusId())
                        .orElseThrow(() -> new RuntimeException("AnimalStatus not found"));
                animal.setAnimalStatus(status);
            }
            
            return convertToDTO(animalRepository.save(animal));
        });
    }

    public boolean deleteAnimal(Long id) {
        return animalRepository.findById(id).map(animal -> {
            animalRepository.delete(animal);
            return true;
        }).orElse(false);
    }

    private AnimalDTO convertToDTO(Animal animal) {
        AnimalDTO dto = new AnimalDTO();
        dto.setId(animal.getId());
        dto.setUserId(animal.getUser() != null ? animal.getUser().getId() : null);
        dto.setAnimalStatusId(animal.getAnimalStatus() != null ? animal.getAnimalStatus().getId() : null);
        dto.setName(animal.getName());
        dto.setType(animal.getType());
        return dto;
    }

    private Animal convertToEntity(AnimalDTO dto) {
        Animal animal = new Animal();
        animal.setName(dto.getName());
        animal.setType(dto.getType());

        if (dto.getUserId() != null) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            animal.setUser(user);
        }

        if (dto.getAnimalStatusId() != null) {
            AnimalStatus status = animalStatusRepository.findById(dto.getAnimalStatusId())
                    .orElseThrow(() -> new RuntimeException("AnimalStatus not found"));
            animal.setAnimalStatus(status);
        }

        return animal;
    }
}
