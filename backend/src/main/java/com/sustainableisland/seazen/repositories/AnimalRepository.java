package com.sustainableisland.seazen.repositories;

import com.sustainableisland.seazen.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
  List<Animal> findByUserId(Long userId);
}
