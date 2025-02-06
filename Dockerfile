# 1st stage: Build the app
FROM maven:3.9.9-amazoncorretto-21-debian-bookworm AS build

# Set the working directory inside the container
ENV APP_HOME /app
WORKDIR $APP_HOME

# Copy the project's POM file and download dependencies
COPY pom.xml $APP_HOME/
RUN mvn dependency:go-offline -DskipTests

# Copy the entire project directory into the container
COPY . $APP_HOME

# Build the project
RUN mvn clean install -DskipTests

# 2nd stage: Build the runtime image
FROM amazoncorretto:21-alpine3.20

# Set the working directory inside the container
ENV APP_HOME /app
WORKDIR $APP_HOME

# Copy the JAR file from the build stage into the runtime image
COPY --from=build $APP_HOME/target/contacts.jar ./

# Specify the command to run when the container starts
CMD ["java", "--enable-preview", "-jar", "contacts.jar"]

# Expose the port that the application will listen on
EXPOSE 8081