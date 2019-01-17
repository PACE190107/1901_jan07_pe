# Java Release Notes
Java has been around since 1995, and has undergone many changes through the years. Version history of Java can be found on the [Java version history wikipedia page](https://en.wikipedia.org/wiki/Java_version_history). Recently, the release schedule was updated to be **every six months**.

Summarized below are the release notes for every recent version of Java along with links to official documentation.

[Link to all official release notes](http://www.oracle.com/technetwork/java/javase/jdk-relnotes-index-2162236.html)

## Java 10
[Official documentation](https://docs.oracle.com/javase/10/)

Released on March 23, 2018, Java 10 includes the following new features:
* Local Variable Type Inference
  * New syntax: `var x = "foo"` will create variable `x` of type `String` (compiler infers the type from RHS)
* Garbage-collector interface
* `Optional.orElseThrow()` method added
* APIs for creating Unmodifiable Collections
* System Property to Disable JRE Last Usage Tracking
* Bytecode Generation for Enhanced for Loop
* Comment Tag for Summary of an API Description (`@summary`)

## Java 9
[Official documentation](https://docs.oracle.com/javase/9/)

Java 9 highlights:
* Java Platform Module System
  * Enhances encapsulation and dependency management
* Linking using jlink tool
  * Create minimal runtime image optimized for your app
* JShell - interactive Java REPL
  * Can launch from command line (`jshell`)
* Javadoc improvements (search and HTML 5 compliant)
* Collection factory methods
  * Directly populate collections: `Set<Integer> ints = Set.of(1, 2, 3);`
* Stream API improvements
  * `Stream` interface methods: `dropWhile()`, `takeWhile()`, `ofNullable()`
  * Turn an `Optional` object into a `Stream`: `Stream<Integer> s = Optional.of(1).stream();`
* Private interface methods
* Support for HTTP/2 and WebSockets with new `HttpClient` API
* Multi-release JARs

## Java 8
[Official documentation](https://docs.oracle.com/javase/8/)

Java 8 was a big release with many new additions to the Java Standard Library. Some highlights include:
* `.forEach()` method in `Iterable` interface
* default and static methods in interfaces
* Functional interfaces and Lambda expressions
* Java Stream API (under `java.util.stream`) for bulk data operations on Collections
* `Optional` class added - eliminates need for `null` checking
* Method referencing - `A::B` syntax, where B is a method in class A (e.g. `TreeSet::new` or `Person::getName`)
* Java Date and Time API (under `java.time`)
  *  `java.time.format` and `java.time.zone` sub-packages for formatting and working with time zones
  * `DateTimeFormatter` class for converting datetime objects to strings
* Collection API improvements
  * `Iterator` default method `forEachRemaining(Consumer action)`
  * `Collection` default method `removeIf(Predicate filter)`
  * `Collection` method `spliterator()` that returns `Spliterator` instance
  * `Map` methods `replaceAll()`, `compute()`, and `merge()`
* Concurrency API improvements
  * `ConcurrencyHashMap` methods `compute()`, `forEachEntry()`, `forEachKey()`, `forEachValue()`, `merge()`, `reduce()`, and `search()`
  * `CompletableFuture` that may be explicitly completed

## Java 7
[Official documentation](https://docs.oracle.com/javase/7/)

Java 7 highlights:
* Diamond operator for type inference
  * Old syntax: `Map<String, List<Trade>> trades = new TreeMap<String, List<Trade>> ();`
  * New syntax: `Map<String, List<Trade>> trades = new TreeMap <> ();`
* Using `String`s in `switch` statements
* Automatic resource management with try-with-resources
  * New syntax: `try(resource){...}` will automatically close the resource
  * Resources to be auto-closed must implement the `java.lang.AutoCloseable` interface
* Numeric literals with underscores
  * `int million  =  1_000_000`
* Multi-catch block
  * `try {...} catch(ExceptionOne | ExceptionTwo | ExceptionThree e) {...}`
* File system API improvements (`java.nio` package)
* Fork and Join - `ForkJoinPool` and `ForkJoinTask`
* Support for dynamic languages
  * New package: `java.lang.invoke`

## Java 6
[Official documentation](https://docs.oracle.com/javase/6/docs/api/)

Java 6 highlights:
* Scripting language support
* Java Compiler API

## Java 5
[Official documentation](https://docs.oracle.com/javase/1.5.0/docs/)

Java 5 highlights:
* For-each loops (enhanced for loops)
  * `for(data_type variable : array | collection) {...}`
* Variable arguments (varargs)
  * `public void methodname(String p1, String ...otherParams) {...}` or `public void methodname(String p1, String... otherParams) {...}`
* Static imports
* Auto-boxing and unboxing
* Enumerations (`enum`s)
* Covariant return types
* Annotations
* Generics - improved type safety
