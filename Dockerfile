# Use Java 17 OpenJDK
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and project files
COPY . .

# Build the app
RUN ./mvnw clean package -DskipTests -Dstyle.color=never

# Expose port
EXPOSE 8080

# Run the jar
CMD ["java", "-jar", "target/*.jar"]
