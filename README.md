# Spring Data JPA Example

Very simple demo explainatory demo exhibiting some of the basics of Spring Data JPA, and demonstrating a way to expose
multiple views of the same model.

#### Widgets, Sprockets, and Categories
The data model is excessively simple, yet ridiculously abstract. The point of this is to focus on the features provided by Spring Data
JPA / Spring Boot, rather than the details of the application.

The model consists of a single one-to-many relationship, and another many-to-many relationship. Each [`Widget`](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/model/Widget.java) 
contains multiple
[`Sprockets`](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/model/Sprocket.java). `Sprockets` can be found in one of several [`Categories`](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/model/Category.java). Both `Sprockets` and `Widgets` have enumerated `Type` values.
 `Categories` have an enumerated `Color` value (why not?).

### Multiple Joins

Spring JPA Repositories are fueled by reflection (read: voodoo and black magic). You just provide an interface which is filled out
with standard SQL queries based solely on the name of the method. The direct mapping from repository methods to queries feels largely
undocumented. On the other hand, it's a lot harder to write SQL that's vulnerable to injection attacks when you're not writing any SQL.

This means that you can spend hours figuring out how to get a JPQL query to work (see [WidgetRepository:32](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/repository/WidgetRepository.java#L33)) or learn some black magic by
trial-and-error with repository names (see [WidgetRepository:42](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/repository/WidgetRepository.java#L42)).

### WidgetViews

One design decision made here is to expose multiple views of a `Widget` via interfaces. One is not always interested in
all the detail found in an entity, and sometimes you want to project or expose different views for security purposes. The
[`WidgetViews`](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/model/views/WidgetViews.java) class provides a common namespace for all of the different views of a Widget. It contains the `IdOnly` and
`SprocketsOnly` views, which are just nested interfaces implementing getters for the data that is exposed by the view.
The `Widget` class implements `SprocketsOnly` and `IdOnly`, to facilitate type-casting. 

This which is just one way to expose only certain attributes to the consumer of a REST API. I am finding that I like this a
little better than DTOs, which I now believe should only be used for queries that return properties from multiple entities.

### Streaming Cursors

Spring Data JPA can return large chunks of data as part of a java.util.stream.Stream, rather than making a single 
massive call to the database to return all of the data. See [`WidgetRepository::findAllStreamBy`](https://github.com/NiftySoft/example-spring-data-jpa/blob/master/src/main/java/com/niftysoft/example/repository/WidgetRepository.java#L48).

Please know that Spring **ignores** most things between `find`, `findAll`, or `findDistinct` and `By`. You can insert your own words
there to distinguish between different methods. This does not appear to be very well documented at this time.

**Caveat:** This still needs to be tested against an actual DB container. (H2 doesn't count!)

### WidgetAsyncRepository

Spring Data JPA has support for Asynchronous queries which return a Future. This can increase throughput by running
multiple unrelated database calls in parallel, but also needs to be tested against an actual DB container. See `WidgetRepository::*Async*`.

**Caveat:** This still needs to be tested against an actual DB container. (H2 doesn't count!)

### Related Projects

If you want to learn more, check out [the official Spring examples](https://github.com/spring-projects/spring-data-examples).

