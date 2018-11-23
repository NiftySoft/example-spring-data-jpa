package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * WidgetRepository is a Spring Data JPA repository for Widgets.
 */
public interface WidgetRepository extends BaseRepository<Widget> {
    Widget save(Widget entity);

    Optional<Widget> findById(Long id);

    Long count();

    List<Widget> findAllByType(Widget.Type type);

    void deleteById(Long id);
    void delete(Widget entity);

    Boolean existsById(Long id);

    // Streaming with Spring Data JPA.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-streaming
    Stream<Widget> findAll();

    // Projection using static inner view interfaces. Only the data which is accessible from the requested interface is
    // retrieved from the DB.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.closed
    interface SprocketsOnly extends BaseRepository<Widget> {
        WidgetViews.SprocketsOnly findById(Long id);
        List<WidgetViews.SprocketsOnly> findAll();
    }
}
