import com.ewerk.gradle.plugins.tasks.QuerydslCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.9"
	id("io.spring.dependency-management") version "1.1.0"
	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.8.22"
	kotlin("kapt") version "1.8.22"
}

group = "ollie"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val querydslVersion = "5.0.0"
val querydslDir = "$buildDir/generated/querydsl"

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-batch")
	implementation("org.springframework.boot:spring-boot-starter-quartz")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

	// querydsl
	implementation ("com.querydsl:querydsl-jpa:${querydslVersion}:jakarta")
	kapt ("com.querydsl:querydsl-apt:${querydslVersion}:jakarta")
	kapt ("jakarta.annotation:jakarta.annotation-api")
	kapt ("jakarta.persistence:jakarta.persistence-api")

	// cloud
	implementation("io.awspring.cloud:spring-cloud-starter-aws-secrets-manager-config:2.4.4")
	implementation("com.google.firebase:firebase-admin:9.2.0")
	implementation("com.squareup.okhttp3:okhttp:4.12.0")

	// apache
	implementation("commons-io:commons-io:2.15.0")

	// springdoc-openapi
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.2.0")

	// serialization
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

	// security, jwt, oauth2
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

	runtimeOnly("com.mysql:mysql-connector-j")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.batch:spring-batch-test")
	testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
	testImplementation("io.kotest:kotest-assertions-core:5.7.2")
	testImplementation("io.mockk:mockk:1.12.0")
}

querydsl {
	jpa = true
	querydslSourcesDir = querydslDir
}

sourceSets.getByName("main") {
	java.srcDir(querydslDir)
}

configurations {
	named("querydsl") {
		extendsFrom(configurations.compileClasspath.get())
	}
}

tasks.withType<QuerydslCompile> {
	options.annotationProcessorPath = configurations.querydsl.get()
}

noArg {
	annotation("jakarta.persistence.Entity")
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.Embeddable")
	annotation("jakarta.persistence.MappedSuperclass")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
