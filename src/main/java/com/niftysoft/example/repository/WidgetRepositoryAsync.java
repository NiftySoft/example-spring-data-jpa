package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;

// Asynchronous queries with Spring Data JPA.
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-async
public interface WidgetRepositoryAsync extends Repository<Widget, Long> {
    @Async
    Future<Optional<Widget>> findById(Long id);

    @Async
    Future<Long> count();

    @Async
    Future<List<Widget>> findAllByType(Widget.Type type);

    @Async
    Future<Boolean> existsById(Long id);

    interface SprocketsOnly extends Repository<Widget, Long> {
        @Async
        Future<WidgetViews.SprocketsOnly> findById(Long id);

        @Async
        Future<List<WidgetViews.SprocketsOnly>> findAll();
    }
}
