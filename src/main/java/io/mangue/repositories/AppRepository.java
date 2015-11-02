package io.mangue.repositories;

import io.mangue.models.App;
import io.mangue.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppRepository extends MongoRepository<App, String> {
}