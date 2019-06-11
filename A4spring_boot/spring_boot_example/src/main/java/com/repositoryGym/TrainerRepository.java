package com.repositoryGym;

import com.modelGym.Trainer;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.mongodb.Mongo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TrainerRepository extends ReactiveMongoRepository<Trainer, String> /*extends JpaRepository<Trainer, Integer>*/ {
    Mono<Trainer> findByName(String name);
    Mono<Trainer> findById (Integer name);
//    @Override
    Flux<Trainer> findAll();
}
