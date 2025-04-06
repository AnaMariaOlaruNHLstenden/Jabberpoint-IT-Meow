# Jabberpoint

## About
JabberPoint is a primitive slide-show program in Java(tm). It is freely copyable as long as you keep this notice and the splash screen intact.

This is a modified version of the original JabberPoint project, adapted for educational purposes at NHL Stenden University of Applied Sciences.

Original Copyright (c) 1995-1997 by Ian F. Darwin, ian@darwinsys.com.
Adapted by Gert Florijn (version 1.1) and Sylvia Stuurman (version 1.2 and higher) for the Open University of the Netherlands, 2002 -- now.

## Requirements

- [Java 23 SDK](https://www.oracle.com/java/technologies/downloads/#java23) - Download the latest Java 23 SDK from Oracle
- [Maven 3.8.0 or higher](https://maven.apache.org/download.cgi) - Download the latest Maven from Apache or if you have chocolatey you can write "choco install maven" in powershell

## Dependencies

- JUnit 5.10.0 (for testing)
- Mockito 5.16.1 (for testing)

## Building and Running

This project uses Maven for dependency management and building. To build the project:

```bash
mvn clean install
```

## Distribution

The project is configured to use GitHub Packages for distribution.

