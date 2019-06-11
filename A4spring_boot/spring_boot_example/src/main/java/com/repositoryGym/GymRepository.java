package com.repositoryGym;

import com.modelGym.Gym;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.mongodb.Mongo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@Transactional
@Repository
public interface GymRepository extends ReactiveMongoRepository<Gym, String> /*extends JpaRepository<Gym, Integer> */{
    Mono<Gym> findByName(String name);
    Mono<Gym> findById (Integer name);
    Flux<Gym> findAll();
}
