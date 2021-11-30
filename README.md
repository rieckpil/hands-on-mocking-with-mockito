[![Mockito Course](https://rieckpil.de/wp-content/uploads/2020/11/hands-on-mocking-with-mockito-course-logo.png)](https://rieckpil.de/hands-on-mocking-with-mockito-online-course/)

# Hands-On Mocking With Mockito Online Course

[![Build & Test Maven Project](https://github.com/rieckpil/hands-on-mocking-with-mockito/workflows/Build%20&%20Test%20Maven%20Project/badge.svg)](https://github.com/rieckpil/hands-on-mocking-with-mockito/actions)

## Introduction

Build your Java testing success on top of a solid foundation.

With this right-sized online course you get a practical introduction to Mockito and learn about all its features.

## Further Resources and Links

* [Course Landing Page with FAQ](https://rieckpil.de/hands-on-mocking-with-mockito-online-course/#FAQ)
* [Course Overview](https://rieckpil.de/courses/hands-on-mocking-with-mockito/)
* [Course Login](https://rieckpil.de/wp-login.php)
* [Password Reset](https://rieckpil.de/wp-login.php?action=lostpassword)

## Local Project Setup

Mandatory requirements:

* Java 11 (JDK flavour (OpenJDK/Azul/Oracle) does not matter). For the correct Java version setup I can recommend [JEnv](https://www.youtube.com/watch?v=9FVZyeFDXo0) (Mac/Linux) and the [Maven Toolchains Plugin](https://maven.apache.org/plugins/maven-toolchains-plugin/toolchains/jdk.html) (Windows)

```
$ java -version
openjdk version "11.0.9.1" 2020-11-04
OpenJDK Runtime Environment (build 11.0.9.1+1-Ubuntu-0ubuntu1.20.04)
OpenJDK 64-Bit Server VM (build 11.0.9.1+1-Ubuntu-0ubuntu1.20.04, mixed mode, sharing)
```

Next, run all tests with:

```
./mvnw test (Linux/Mac)
mvnw.cmd test (Windows)
```
