package com.niftysoft.example.repository;

import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * WidgetRepository is a Spring Data JPA repository for Widgets.
 */
public interface WidgetRepository extends CrudRepository<Widget, Long> {

    List<Widget> findAllByType(Widget.Type type);

    // Streaming with Spring Data JPA.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-streaming
    Stream<Widget> findAllStreamBy();

    // Projection using static inner view interfaces. Only the data which is accessible from the requested interface is
    // retrieved from the DB.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.closed
    List<WidgetViews.SprocketsOnly> findAllSprocketsOnlyByType(Widget.Type type);

    List<WidgetViews.SprocketsOnly> findAllSprocketsOnlyBy();

    Optional<WidgetViews.SprocketsOnly> findAllSprocketsOnlyById(Long id);

    // Asynchronous queries with Spring Data JPA.
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-async
    Future<List<Widget>> findAllAsyncBy();

    Future<Optional<Widget>> findAsyncById(Long id);

    Future<Long> countAsyncBy();

    Future<Boolean> existsAsyncById(Long id);

    Future<WidgetViews.SprocketsOnly> findSprocketsOnlyAsyncById(Long id);

    Future<List<Widget>> findAllAsyncByType(Widget.Type type);

    Future<List<WidgetViews.SprocketsOnly>> findAllSprocketsOnlyAsyncBy();
}
