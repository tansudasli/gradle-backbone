# gradle-backbone

This project is a single-module project which also covers some critical concepts as a backbone

- Test concepts (jmh, junit5) 
- Containerization (docker, palantir)
- Native image (graalvm) generation

<details>
<summary>Tech. stack</summary>

- jdk19
- gradle 7.6
- 
</details>

## more on gradle

Project scope and java command scopes have different things! So Some key points to consider are:

- `./gradlew run`, checks `application {mainClass=}` attribute in `build.gradle` 
- `java -jar ..jar`, checks main manifest file in jar or `{ manifest { attributes {} }}` in `build.gradle` 
- `java -cp ..jar core.org.MainBlaBla`, where leads no main manifest attribute. 

<details>
<summary>gradle multi-modules injections</summary>

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
</details>

 ## more on graalvm & native image capability

- Install sdkman`brew install sdkman-cli`
- Install graalvm java `sdk install java 22.3.r19-grl` for jdk19
- Install native-image `gu install native-image`