# Spring Data JPA Example

Very simple demo explainatory demo exhibiting some of the basics of Spring Data JPA, and demonstrating a way to expose
multiple views of the same model.

#### Widgets, Sprockets, and Categories
The data model is excessively simple, yet ridiculously abstract. The point of this is to focus on the features provided by Spring Data
JPA / Spring Boot, rather than the details of the application.

The model consists of a single one-to-many relationship, and another many-to-many relationship. Each `Widget` contains multiple
`Sprockets`. `Sprockets` can be found in one of several `Categories`. Both `Sprockets` and `Widgets` have a `Type`. `Categories`
have a `Color` (why not?).

### WidgetViews

One design decision made here is to expose multiple views of a `Widget` via interfaces. One is not always interested in
all the detail found in a widget, and you want to project or expose different views for security purposes. The `WidgetViews`
class provides a common namespace for all of the different views of a Widget. It contains the `IdOnly` and `SprocketsOnly`
views, which are just read-only Java interfaces implementing the subset of the data exposed by the view.

Access to these views is provided via the `SprocketsOnly` interface to `Widget`. `Widget` implements `SprocketsOnly`, 
which is just one way to expose only certain attributes to the consumer of a REST API. I am finding that I like this a
little better than DTOs, which I now believe should only be used for data results that involve multiple classes of Entities
in one request.

### Streaming Cursors

Spring Data JPA can return large chunks of data as part of a java.util.stream.Stream, rather than making a single 
massive call to the database to return all of the data. See `WidgetRepository::findAllStreamBy`. 

**Caveat:** This still needs to be tested against an actual DB container. (H2 doesn't count!)

### WidgetAsyncRepository

Spring Data JPA has support for Asynchronous queries which return a Future. This can increase throughput by running
multiple unrelated database calls in parallel, but also needs to be tested against an actual DB container. See `WidgetRepository::*Async*`.

**Caveat:** This still needs to be tested against an actual DB container. (H2 doesn't count!)

