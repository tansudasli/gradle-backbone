# gradle-backbone

This project is single-module project which also covers some critical concepts as a backbone
- Test concepts (jmh, junit5) 
- Containerization (docker, palantir)


## more on gradle
Project scope and java command scopes have different things! So Some key points to consider are:

- `./gradlew run`, checks `application {mainClass=}` attribute in `build.gradle` 
- `java -jar ..jar`, checks main manifest file in jar or `{ manifest { attributes {} }}` in `build.gradle` 
- `java -cp ..jar core.org.MainBlaBla`, where leads no main manifest attribute. 

### gradle multi-modules injections

- Think about some kind of key points 
  - Project structure (many modules, all contains `build.gradle`, `src` folders etc..)
  - Handling some central `build.gradle` staffs (i.e some shared parts)
  - Unlock multi-modules marked `build.gradle` lines
  - Define project dependencies (gradle command level and java command level)
    - [x] if you use java commands: (i.e. _fp_, depends on the _core_ project jar).
          so add this jar, too.
        - `java -cp core/build/libs/core-1.0-SNAPSHOT.jar:fp/build/libs/fp-1.0-SNAPSHOT.jar org.example.Main`
        - or put the core jar into a local repository, then let gradle get from there !

    - [x] if, you use gradle commands
      - In multi-modules, project specific commands `./gradlew :module-name:run` also possible

 