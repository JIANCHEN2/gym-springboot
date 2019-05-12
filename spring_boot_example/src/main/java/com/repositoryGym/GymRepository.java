package com.repositoryGym;

import com.modelGym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;


//@Transactional
public interface GymRepository extends JpaRepository<Gym, Integer> {
    Gym findByName(String name);
}
