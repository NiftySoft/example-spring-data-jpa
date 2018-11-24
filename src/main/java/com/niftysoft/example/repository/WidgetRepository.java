package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
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

    // Asynchronous queries with Spring Data JPA.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-async
    // UPSTREAM BUG?
    // When this inner repository is uncommented, the controller at WidgetControllerIT produces an argument type mismatch
    // exception. Removing it resolves the issue. Why is spring data JPA looking at a nested inner class to attempt
    // to resolve DomainClassConverter?
    interface Asynchrony extends Repository<Widget, Long> {
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
}
