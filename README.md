# Software Testing Lab Four: Library Alert System

Project for testing the `LibraryService` class using JUnit 5, Mockito, and Maven.

## Technologies Used

- **Java 11** — programming language
- **JUnit 5** — unit testing framework
- **Mockito** — mocking dependencies such as the email provider and resource repository
- **Apache Maven** — dependency management and test execution
- **JaCoCo** — code coverage reporting

## Prerequisites

Ensure the following are installed before running the project:

- **Java JDK 11 or later** — required to compile and run the Java code.
- **Apache Maven** — required to manage dependencies and execute tests.
- **IntelliJ IDEA (optional)** — recommended IDE for opening the project and running tests.

Verify Java and Maven installations using:

```bash
java -version
mvn -version
```

JUnit 5, Mockito, and JaCoCo are configured in `pom.xml`. Maven downloads these dependencies automatically when the project is built.

## Installation

1. Clone the repository.

   ```bash
   git clone https://github.com/polarmilk/software-testing-lab4
   ```

2. Open the project in IntelliJ IDEA or another Java IDE.
3. Ensure Maven dependencies are loaded.

## Running Tests

**Test Results:** 5/5 tests passing.

### Maven

From the project root:

```bash
mvn clean test
```

### Alternative: Run through IntelliJ IDEA

Open:

```text
src/test/java/LibraryServiceTest.java
```

Right-click `LibraryServiceTest` and select **Run 'LibraryServiceTest'**.

## Test Location

The unit tests are located at:

```text
src/test/java/LibraryServiceTest.java
```

The implementation is located at:

```text
src/main/java/Library/LibraryService.java
```

## Coverage

JaCoCo coverage reports are generated when running:

```bash
mvn test
```

- **Code Coverage:** 100%
- **Branch Coverage:** 100%
- **Branches Missed:** 0

The report can be found at:

```text
target/site/jacoco/index.html
```

Open this file in a browser to view the coverage results.

## Documentation

See the `doc/` directory for:

- **Problem Specification** — requirements and expected behavior
- **Test Design** — test cases and testing approach
