# Basic Guide to Spring/Spring Boot/Maven

## Spring and Spring Boot

Full read: [baeldung-resource](https://www.baeldung.com/spring-vs-spring-boot)

### Spring
The Spring framework provides comprehensive infrastructure support for developing Java applications.

It’s packed features like Dependency Injection, and out of the box modules like:

- **Spring JDBC**: Simplifies database access and error handling. It provides a template pattern approach to interact with a database, reducing the need for boilerplate code.

- **Spring MVC**: A framework for building web applications in Java. It follows the Model-View-Controller design pattern and offers features for developing web pages, RESTful web services, and more.

- **Spring Security**: Provides comprehensive security services for Java applications. It offers a wide range of authentication and authorization features to secure applications.

- **Spring AOP** (Aspect-Oriented Programming): Allows the separation of cross-cutting concerns (such as logging, transaction management) from the business logic, making the code cleaner and more maintainable.

- **Spring ORM** (Object-Relational Mapping): Integrates with ORM frameworks like Hibernate, JPA, and JDO. It simplifies the data access process while working with databases in object-oriented programming languages.

- **Spring Test**: Offers testing support for Spring components. It simplifies the process of writing unit and integration tests by providing mock objects and testing frameworks integration.

## Spring Bean

A **Spring Bean** is an object that is instantiated, assembled, and otherwise managed by the Spring IoC (Inversion of Control) container. At its core, it's a component of your application that Spring is aware of and manages throughout its lifecycle. Here’s a quick breakdown:

Additional information -> [spring-bean](https://www.baeldung.com/spring-bean)

### Instantiation

Spring creates instances of classes that are defined as beans. This creation process is typically configured in a Spring configuration file using XML or through annotations in the code.

### Assembly

Spring injects dependencies into the beans as needed. A dependency is another object that a bean requires to perform its tasks. For instance, a `UserService` bean might depend on a `UserRepository` bean to retrieve user information.

### Management

After beans are created and assembled, Spring takes care of their entire lifecycle. This includes instantiating bean objects, invoking initialization methods, and destroying them when necessary.

Beans are defined in the Spring configuration file (XML) or via annotations. They can be accessed and manipulated through the Spring application context. Central to the Spring framework, beans enable the construction of an application in an interchangeable and reusable manner, forming the application's backbone.

## Spring Boot
Spring Boot is basically an extension of the Spring framework, which eliminates the boilerplate configurations required for setting up a Spring application.

*It takes an opinionated view of the Spring platform, which paves the way for a faster and more efficient development ecosystem.*

Features
- Create stand-alone Spring applications
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Provide opinionated 'starter' dependencies to simplify your build configuration
- Automatically configure Spring and 3rd party libraries whenever possible
- Provide production-ready features such as metrics, health checks, and externalized configuration
- Absolutely no code generation and no requirement for XML configuration

Spring Boot provides a number of starter dependencies for different Spring modules. Some of the most commonly used ones are:

- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-test
- spring-boot-starter-web
  
## Maven Explained

Apache Maven is a comprehensive project management tool used primarily for Java projects. It automates the process of building and managing projects, adhering to the principle of convention over configuration. Maven simplifies tasks such as compiling code, packaging binaries, and managing dependencies through the use of a Project Object Model (POM) file, typically named `pom.xml`.

### Build Lifecycle

Maven organizes the build process into a series of phases known as a build lifecycle. Key lifecycles include:

- `validate` - validate the project is correct and all necessary information is available
- `compile` - compile the source code of the project
- `test` - test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
- `package` - take the compiled code and package it in its distributable format, such as a JAR.
- `verify` - run any checks on results of integration tests to ensure quality criteria are met
- `install` - install the package into the local repository, for use as a dependency in other projects locally
- `deploy` - done in the build environment, copies the final package to the remote repository for sharing with other developers and projects.

Each lifecycle is made up of phases, for example, the default lifecycle includes `compile`, `test`, `package`, and more.

### Goals

Goals are specific tasks within a build phase, such as compiling code or creating a JAR file. Executing a Maven phase triggers all goals associated with that phase.

### Project Object Model (POM)

The `pom.xml` file is at the heart of a project's Maven configuration. It contains project information and configuration details for Maven to build the project. Essential elements of a POM file include dependencies, repositories, plugins, and profiles.

#### Dependencies

Dependencies are external Java libraries required by the project. Each dependency is identified by its `groupId`, `artifactId`, and `version`.

```xml
<dependencies>
    <dependency>
        <groupId>com.example</groupId>
        <artifactId>example-library</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

#### Repositories

Repositories are sources from which Maven can download dependencies. If a dependency is not present in the local repository, Maven will attempt to download it from a configured remote repository.

```xml
<repositories>
    <repository>
        <id>example-repo</id>
        <url>http://example.com/maven2</url>
    </repository>
</repositories>
```

#### Dependency Scopes

Dependency scopes define the classpath visibility and lifecycle of a dependency:

- **compile**: Default scope, available on all classpaths.
- **provided**: Expected to be provided by the JDK or a container at runtime.
- **runtime**: Required for execution but not for compilation.
- **test**: Only available for the test compilation and execution.
- **system**: Similar to provided but the JAR must be explicitly provided.
- **import**: Used in a dependency of type `pom` to import dependency management information.

#### Versioning

Maven supports several strategies for specifying dependency versions:

- **Fixed Version**: Specifies an exact version.
- **Version Ranges**: Allows the use of a range of versions.
- **Snapshot Versions**: Development versions that can be updated with newer snapshots.

### Profiles

Profiles enable customization of the build for different environments or configurations. They can override settings, dependencies, and plugins for specific scenarios.

```xml
<profiles>
    <profile>
        <id>development</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <dependencies>
            <!-- Development-specific dependencies here -->
        </dependencies>
    </profile>
    <profile>
        <id>production</id>
        <!-- Production-specific settings here -->
    </profile>
</profiles>
```

Maven's structure and its `pom.xml` configuration file provide a robust and flexible framework for managing project builds, dependencies, and documentation across different environments and stages of development.