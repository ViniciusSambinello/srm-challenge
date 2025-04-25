plugins {
	java
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	jacoco
}

group = "dev.sambinello"
version = "1.0.0"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(11)
	}
}

repositories {
	mavenCentral()
}

apply {
	from("${rootProject.rootDir}/config/tests.gradle")
	from("${rootProject.rootDir}/config/jacoco.gradle")
}

dependencies {

	/**
	 * Spring boot framework
	 */
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	/**
	 * SQL
	 */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.flywaydb:flyway-core")
	runtimeOnly("org.postgresql:postgresql")

	/**
	 * Utilities
	 */
	compileOnly("org.projectlombok:lombok:1.18.38")
	annotationProcessor("org.projectlombok:lombok:1.18.38")

	/**
	 * Tests
	 */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
	finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
	dependsOn(tasks.test)
}

tasks.check {
	dependsOn(tasks.jacocoTestCoverageVerification)
}

tasks.register("bootRunDev") {
	group = "application"
	description = "Runs the Spring Boot application with the dev profile"
	doFirst {
		tasks.bootRun.configure {
			systemProperty("spring.profiles.active", "dev")
		}
	}
	finalizedBy("bootRun")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
