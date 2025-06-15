# Cache Server

A high-performance caching proxy server built with Spring Boot and Kotlin.

Project URL: [https://roadmap.sh/projects/caching-server](https://roadmap.sh/projects/caching-server)

## Features

- RESTful API endpoints for cache operations
- Command-line interface using Picocli
- JSON data handling with Gson
- Spring Boot web server integration
- Lombok for reduced boilerplate code

## Prerequisites

- Java 21 or higher
- Gradle 8.x or higher

## Building the Project

To build the project, run:

```bash
./gradlew build
```

This will create a runnable JAR file at `build/libs/caching-proxy.jar`

## Running the Application

You can run the application using:

```bash
./gradlew bootRun
```

Or using the generated JAR:

```bash
java -jar build/libs/caching-proxy.jar
```

## Project Structure

```
CacheServer/
├── src/                    # Source code
├── build/                  # Build output
├── gradle/                 # Gradle wrapper files
├── build.gradle.kts        # Gradle build configuration
└── settings.gradle.kts     # Gradle settings
```

## Dependencies

- Spring Boot 3.5.0
- Picocli 4.7.7
- SLF4J 2.0.13
- Gson 2.11.0
- Lombok 1.18.32

## License

This project is licensed under the MIT License - see the LICENSE file for details. 