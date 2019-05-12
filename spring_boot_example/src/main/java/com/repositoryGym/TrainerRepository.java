package com.repositoryGym;

import com.modelGym.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    List<Trainer> findByName(String name);
}
