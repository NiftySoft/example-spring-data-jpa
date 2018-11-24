package com.niftysoft.example.repository;

import com.niftysoft.example.model.Sprocket;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.dto.IdDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * SprocketRepository is a repository for Sprockets.
 */
public interface SprocketRepository extends CrudRepository<Sprocket, Long> {
    List<Sprocket> findAllByType(Sprocket.Type type);
}
