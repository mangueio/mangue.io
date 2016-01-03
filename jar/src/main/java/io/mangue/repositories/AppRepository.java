package io.mangue.repositories;

import io.mangue.models.App;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppRepository extends MongoRepository<App, String> {

    public List<App> findByUserIds(@Param("userId") String userId);
}