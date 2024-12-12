import org.springframework.boot.gradle.tasks.bundling.BootBuildImage
import java.text.SimpleDateFormat
import java.util.*


plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}

version = "0.0.49.1"
group = "com.nohadon"


java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}


repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")
	runtimeOnly("com.mysql:mysql-connector-j")
	implementation("org.kohsuke:github-api:1.326")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	implementation("com.h2database:h2:2.2.220")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.named<BootBuildImage>("bootBuildImage") {
	val buildDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(Date())
	createdDate.set(buildDate)
	imageName.set("nohadon-files-boot:$version")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
