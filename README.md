# Bash is evil 😈 Use Gradle!

This repository has been created for the Kotland conference talk `Bash is evil, write a Gradle Plugin with Kotlin instead!`.

The slides are available [here](https://docs.google.com/presentation/d/1vP9MKOvqXMw05KVrOaC5X0wt09wwKkYkGS3XAwNAs5I/edit?usp=sharing).

This project shows how to control fairly complex build logic using Gradle. In this case, a JVM web server wrapped inside a Docker container.

# Build

Be sure to have `JAVA_HOME` env variable correctly setup and Docker installed. Then you can run:
- `gradlew dockerStagingBuild`
- `gradlew dockerProductionBuild`

Each will build the respective container.