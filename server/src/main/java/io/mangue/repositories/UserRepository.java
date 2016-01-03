package io.mangue.repositories;

import io.mangue.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  public User findByUsername(String username);
}