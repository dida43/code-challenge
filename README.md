# Code Challenge

## Description

This application is completed as a task following
this [assigment](https://github.com/softwaresauna/code-challenge). The result is a path following
algorithm in ASCII Map.

## Requirements

User should have Maven build tool and Java (JDK, minimum version 8) installed on the system. It may
be worth noting that the application was developed in Java 8 and tested using Apache Maven 3.6.1 on
MacOS.

## Installation

To install the application and be able to use `code-challenge.jar` from command line, user should
run command

    $ mvn clean install

Command will run tests and build executable code-challenge.jar with example files in directory

    target/code-challenge-app

## Usage

User should position itself into target/code-challenge-app directory and run command:

    $ java -jar code-challenge.jar /path/to/file

where */path/to/file* is path to ASCII Map file

### Examples

Directory target/code-challenge-app/examples contains list of basic example ASCII Maps which can be
used to test the application. Below are some examples of application runs.

#### Example 1

ASCII Map:

```
@---A---+
        |
x-B-+   C
    |   |
    +---+
```

Command output:

```
$ java -jar code-challenge.jar examples/01ABasicExample 
Letters ACB
Path as characters @---A---+|C|+---+|+-B-x
```

#### Example 2

ASCII Map:

```
@
| +-C--+
A |    |
+---B--+
  |      x
  |      |
  +---D--+
```

Command output:

```
$ java -jar code-challenge.jar examples/02GoStraightThroughIntersections 
Letters ABCD
Path as characters @|A+---B--+|+--C-+|-||+---D--+|x
```

### Running tests

As mentioned in Installation chapter, tests execute while installing the app. To only run tests user
can use the command:

    $ mvn test

This command will build source files and run tests.

#### Test output

```
$ mvn test
[INFO] Scanning for projects...
[INFO] 
[INFO] ---------------------< org.dida43:code-challenge >----------------------
[INFO] Building code-challenge 1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:resources (default-resources) @ code-challenge ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 0 resource
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ code-challenge ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-resources-plugin:3.2.0:testResources (default-testResources) @ code-challenge ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Using 'UTF-8' encoding to copy filtered properties files.
[INFO] Copying 16 resources
[INFO] 
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ code-challenge ---
[INFO] Nothing to compile - all classes are up to date
[INFO] 
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ code-challenge ---
[INFO] Surefire report directory: /Users/dida43/git/code-challenge/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running org.dida43.map.walker.enums.NonPathCharactersTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.003 sec
Running org.dida43.map.walker.enums.PathDirectionTest
Tests run: 31, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.012 sec
Running org.dida43.map.walker.enums.PathCharactersTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running org.dida43.map.walker.enums.LetterCharactersTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running org.dida43.map.walker.map.AsciiMapTest
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.008 sec
Running org.dida43.map.walker.map.AsciiMapWalkerTest
Tests run: 16, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.049 sec
Running org.dida43.map.walker.pojos.PositionTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 64, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.668 s
[INFO] Finished at: 2021-10-25T10:45:55+02:00
[INFO] ------------------------------------------------------------------------
MacBook-Pro:code-challenge dida43$ 

```