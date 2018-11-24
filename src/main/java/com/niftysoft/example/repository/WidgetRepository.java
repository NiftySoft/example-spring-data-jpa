package com.niftysoft.example.repository;

import com.niftysoft.example.model.Category;
import com.niftysoft.example.model.Widget;
import com.niftysoft.example.model.views.WidgetViews;
import org.springframework.data.jpa.repository.Query;
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

    /**
     * Using JPQL... just because you can doesn't mean you should. See findDistinctBySprocketsCategories for
     * an easier way.
     *
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.at-query
     * https://docs.oracle.com/html/E13946_01/ejb3_langref.html
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-property-expressions
     *
     * @param categoryId Long
     * @return List<Widget> all Widgets which contain a sprocket in the requested category.
     */
    @Query("SELECT DISTINCT w FROM Widget w JOIN w.sprockets s JOIN s.categories c WHERE c.id = ?1")
    List<Widget> findAllBySprocketCategoryId(Long categoryId);

    default List<Widget> findAllBySprocketCategory(Category category) {
        return findAllBySprocketCategoryId(category.getId());
    }

    /**
     * This method re-implements the double join in findAllBySprocketCategoryId
     */
    List<Widget> findDistinctBySprocketsCategories(Category category);

    /**
     * Streaming with Spring Data JPA.
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-streaming
     */
    Stream<Widget> findAllStreamBy();

    /**
     * Projection using static inner view interfaces. Only the data which is accessible from the requested interface is
     * retrieved from the DB.
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections.interfaces.closed
     */
    List<WidgetViews.SprocketsOnly> findAllSprocketsOnlyByType(Widget.Type type);

    List<WidgetViews.SprocketsOnly> findAllSprocketsOnlyBy();

    Optional<WidgetViews.SprocketsOnly> findAllSprocketsOnlyById(Long id);

    /**
     * Asynchronous queries with Spring Data JPA.
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-async
     */
    Future<List<Widget>> findAllAsyncBy();

    Future<Optional<Widget>> findAsyncById(Long id);

    Future<Long> countAsyncBy();

    Future<Boolean> existsAsyncById(Long id);

    Future<WidgetViews.SprocketsOnly> findSprocketsOnlyAsyncById(Long id);

    Future<List<Widget>> findAllAsyncByType(Widget.Type type);

    Future<List<WidgetViews.SprocketsOnly>> findAllSprocketsOnlyAsyncBy();
}
