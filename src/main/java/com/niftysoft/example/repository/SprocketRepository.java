package com.niftysoft.example.repository;

import com.niftysoft.example.model.Sprocket;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * SprocketRepository is a repository for Sprockets.
 */
public interface SprocketRepository extends CrudRepository<Sprocket, Long> {
    List<Sprocket> findAllByType(Sprocket.Type type);
}
