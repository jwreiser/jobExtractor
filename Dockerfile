#FROM eclipse-temurin:17-jre-alpine
FROM maven:3.9.6-eclipse-temurin-22
RUN mkdir /app
WORKDIR /app

COPY pom.xml .
COPY src src


RUN addgroup --system jobgroup
RUN adduser --system jobuser
#RUN adduser -S -s /bin/false -G spring spring
#RUN adduser jobuser jobgroup 
RUN chown -R jobuser:jobgroup /app
USER jobuser

RUN mvn compile -DskipTests 
RUN --mount=type=cache,target=/root/.m2 mvn package  -DskipTests 

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=dev

HEALTHCHECK --interval=30s --timeout=3s --retries=1 CMD wget -qO- http://localhost:8080/actuator/health/ | grep UP || exit 1

ENTRYPOINT ["java","--enable-preview","-jar","/app/target/jobExtractor-0.0.2.jar"]
