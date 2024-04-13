
plugins {
    java
    idea
    checkstyle
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "HKA-IWI-1"
version = "0.0.1-alpha"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-artemis")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.named(
    "bootJar",
    org.springframework.boot.gradle.tasks.bundling.BootJar::class.java
) {// Gradle Task: build > assemble
    doLast {
        println(
            """
            |
            |Call of executable JAR-file:
            |Production:
            |   java -jar build/libs/${project.name}-${project.version}.jar --spring.profiles.active=production
            |
            |Development:
            |   java -jar build/libs/${project.name}-${project.version}.jar --spring.profiles.active=dev
            """.trimMargin("|")
        )
    }
}
