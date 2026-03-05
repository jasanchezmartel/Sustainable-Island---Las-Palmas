package com.sustainableisland.seazen.repositories;

import com.sustainableisland.seazen.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
=======
  Optional<User> findByEmail(String email);
  Boolean existsByEmail(String email);
>>>>>>> develop
}
