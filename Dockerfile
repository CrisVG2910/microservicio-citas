# Construcción
FROM eclipse-temurin:21-jdk AS buildstage

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_UGNFM3WC8EM1NFQI /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

# Ejecución
FROM eclipse-temurin:21-jdk

COPY --from=buildstage /app/target/microservicio_citas-0.0.1-SNAPSHOT.jar /app/microservicio_citas.jar

COPY Wallet_UGNFM3WC8EM1NFQI /app/wallet

ENV TNS_ADMIN=/app/wallet
EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "/app/microservicio_citas.jar" ]
