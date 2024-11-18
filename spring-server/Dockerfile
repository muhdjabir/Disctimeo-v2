FROM amazoncorretto:17

# Specify the JAR file built by Gradle
ARG JAR_FILE=build/libs/*.jar

# Copy the JAR file into the image
COPY ${JAR_FILE} application.jar

# Set the default command to run the Java application
ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
