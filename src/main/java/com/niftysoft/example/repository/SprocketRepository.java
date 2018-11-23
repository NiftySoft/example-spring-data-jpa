package com.niftysoft.example.repository;

import com.niftysoft.example.model.Sprocket;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.dto.IdDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * SprocketRepository is a repository for Sprockets.
 */
public interface SprocketRepository extends BaseRepository<Sprocket> {
    List<Sprocket> findAll();
    List<Sprocket> findAllByType(Sprocket.Type type);
}
