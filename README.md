# tdd-ita-course
This Maven project was built using TDD.

The following technologies were used:
- Java-11
- Jackson (JSON processing)
- JUnit-5
- Mockito
- SonarQube
- JaCoCo (Java Code Coverage)
- Log4J

## SonarQube startup

There's a Dockerfile in root path to run Sonarqube and PostgreSQL containerized. Just run the command:
```sh
docker-compose up
```

** If you find some issue related to MAX HEAP during Sonarqube start run the command:
```sh
sudo sysctl -w vm.max_map_count=262144
```
** Remember to reload your Terminal application to get the applied effects

## Maven/JaCoCo/Sonarqube usage

To generate a report and see at Sonarqube do the following:
```sh
mvn clean verify sonar:sonar -Dsonar.projectKey=yourProjectKey -Dsonar.host.url=http://localhost:9000 -Dsonar.login=yourLoginHash
```
Then see the report at: http://localhost:9000/projects