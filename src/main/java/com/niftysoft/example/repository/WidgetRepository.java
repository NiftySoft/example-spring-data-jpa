package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * WidgetRepository is a Spring Data JPA repository for Widgets.
 */
public interface WidgetRepository extends CrudRepository<Widget, Long> {

    List<Widget> findAllByType(Widget.Type type);

    // Projection using static inner view interfaces. Only the data which is accessible from the requested interface is
    // retrieved from the DB.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.closed
    interface SprocketsOnly extends Repository<Widget, Long> {

        List<WidgetViews.SprocketsOnly> findAllByType(Widget.Type type);

        List<WidgetViews.SprocketsOnly> findAll();

        Optional<WidgetViews.SprocketsOnly> findById(Long id);
    }

    interface Streaming extends Repository<Widget, Long> {
        // Streaming with Spring Data JPA.
        // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-streaming
        Stream<Widget> findAll();
    }
}
