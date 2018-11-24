# Spring Data JPA Example

Very simple demo explainatory demo exhibiting some of the basics of Spring Data JPA, and demonstrating a way to expose
multiple views of the same model.

#### Widgets and Sprockets
The data model is excessively simple, but abstract. It consists of two entities which exist in a single one-to-many
relationship. Each `Widget` contains multiple `Sprockets`. Both `Sprockets` and `Widgets` have a type.

### WidgetViews

One design decision made here is to provide multiple views of a `Widget`. One is not always interested in all the detail
found in a widget, and you want to project or expose different views for security purposes. The `WidgetViews` class 
provides a namespace to store different views of a Widget. It contains the `IdOnly` and `SprocketsOnly` views, which
are implemente as Java interfaces.

Access to these views is provided via the `SprocketsOnly` subrepository of the `WidgetRepository`. `SprocketsOnly` is a
Spring Data JPA repository which is an inner interface of the `WidgetRepository`. It makes some sense, in this case,
to use an inner interface rather than a top-level interface, since the `SprocketsOnly` interface is just another view
of a `Widget`. When a caller wants to get the `SprocketsOnly` view of a `Widget`, they still have to go to the
`WidgetRepository` to get it.

Whether this is the greatest way to organize code "subrepositories" is subject to scrutiny. In any case, there's no
question that Spring Data JPA permits it rather easily.

### Streaming Cursors

Spring Data JPA can return large chunks of data as part of a java.util.stream.Stream, rather than making a single 
massive call to the database to return all of the data. This still needs to be tested against an actual DB container.

### WidgetAsyncRepository

Spring Data JPA has support for Asynchronous queries which return a Future. This can increase throughput by running
multiple unrelated database calls in parallel, but also needs to be tested against an actual DB container.

