FROM openjdk:17-oracle

WORKDIR /pokedex
COPY . /pokedex/

ENTRYPOINT [ "java", "/pokedex/src/core/main.java" ]

COPY .mvn