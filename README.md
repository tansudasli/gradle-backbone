# gradle-backbone

This project is a single-module project which also covers some critical concepts as a backbone

- Test concepts (jmh, junit5) 
- Containerization (docker, palantir)
- Native image (graalvm)

<details>
<summary>Tech. stack</summary>

- jdk 19.0.1
- graalvm 22.3
- gradle 7.6

</details>

## how to run

<details>
<summary>IDE</summary>
Keep in mind that IDE, gradle, java commands have different contexts ! May need, different changes at different places

```
  - in intellij settings/java compiler, change additional params with --enable-preview
  - in run/debug configurations, add vm options then add --enable-preview
  - in module settings, switch project/language leves then select experimental settings, if needed
```
</details>

<details>
<summary>docker</summary>
Current Dockerfile config containerize the .jar file. Not the native file.

- preparations

```
  - check Dockerfile, 
  - if you want, copy build/native file (Main, MainPStream etc...) instead of .jar file!
```

- to create the image 
 ```
  - 
  - ./gradlew docker or ./gradlew dockerTagDockerHub
  - docker images
 ```

- to run
```
  - docker run -it gradle-backbone:2.0-SNAPSHOT
  - java -cp gradle-backbone-2.0-SNAPSHOT.jar org.core.Main
```

</details>

<details>
<summary>native image</summary>

- preparations
```
  - complete pre-installations (sdkman, graalvm jdk, native-image etc..)  
  - check graalVM configs in build.gradle file (version, graalvmNative, binaries etc...)
```

- to run
```
  - ./gradlew build jar
  - ./gradlew nativeCompile
  - ./build/native/nativeCompile/gradle-backbone.Main

```

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

 ## more on graalvm

- Install sdkman`brew install sdkman-cli`
- Install graalvm java `sdk install java 22.3.r19-grl` for jdk19
- Install native-image `gu install native-image`