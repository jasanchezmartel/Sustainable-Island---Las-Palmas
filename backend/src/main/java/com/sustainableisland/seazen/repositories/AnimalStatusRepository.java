package com.sustainableisland.seazen.repositories;

import com.sustainableisland.seazen.models.AnimalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalStatusRepository extends JpaRepository<AnimalStatus, Long> {
}
