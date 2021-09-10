import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {

	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"

	id("org.dddjava.jig-gradle-plugin") version "2021.8.4"
}

base {
	archivesName.set("rail-fare-calculation")
}

group = "com.example"
//version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-web:2.5.4")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.4")

	implementation( "io.springfox:springfox-boot-starter:3.0.0")
//	implementation( "org.springdoc:springdoc-openapi-ui:1.5.10")
//	implementation( "org.springdoc:springdoc-openapi-kotlin:1.5.10")

	developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.4")

	testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.4")

	dependencies {
		testImplementation("io.kotest:kotest-runner-junit5:4.6.2") 	// for kotest framework
		testImplementation("io.kotest:kotest-assertions-core") 		// for kotest core jvm assertions
//		testImplementation("io.kotest:kotest-property:4.4.1") 		// for kotest property test
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

sourceSets.getByName("main") {
	java.srcDir("src/main/kotlin/")
}

tasks.named("jigReports") {
	dependsOn("clean", "compileKotlin", "compileJava")
}

tasks.getByName<Jar>("jar") {
	enabled = false
}

tasks.withType<Test> {
	useJUnitPlatform()
}
