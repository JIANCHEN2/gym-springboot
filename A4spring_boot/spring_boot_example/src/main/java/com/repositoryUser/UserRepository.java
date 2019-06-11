package com.repositoryUser;

import com.modelUser.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.mongodb.Mongo;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> /*extends JpaRepository<User, Integer> */{
    Mono<User> findByUsernameAndPassword(String username, String password);
    Mono<User> findByUsername(String username);
}
