package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * WidgetRepository is a Spring Data JPA repository for Widgets.
 */
public interface WidgetAsyncRepository extends BaseRepository<Widget> {
    Widget save(Widget entity);

    @Async
    Future<Optional<Widget>> findById(Long id);

    @Async
    Future<Long> count();

    @Async
    Future<List<Widget>> findAllByType(Widget.Type type);

    void deleteById(Long id);
    void delete(Widget entity);

    @Async
    Future<Boolean> existsById(Long id);

    // Streaming with Spring Data JPA.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-streaming
    Stream<Widget> findAll();

    interface SprocketsOnly extends BaseRepository<Widget> {
        // Projection using static inner view interfaces.
        // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.closed
        @Async
        Future<WidgetViews.SprocketsOnly> findById(Long id);
        @Async
        Future<List<WidgetViews.SprocketsOnly>> findAll();
    }
}
