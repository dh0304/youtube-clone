import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	java
	id("org.springframework.boot") apply false
	id("io.spring.dependency-management") apply false
	id("java-test-fixtures")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

val projectGroup: String by project
val applicationVersion: String by project
allprojects {
	group = projectGroup
	version = applicationVersion

	repositories {
		mavenCentral()
	}
}

val lombokVersion: String by project
subprojects {
	plugins.apply("java")
	plugins.apply("org.springframework.boot")
	plugins.apply("io.spring.dependency-management")
	plugins.apply("java-test-fixtures")

	dependencies {
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")

		compileOnly("org.projectlombok:lombok:${lombokVersion}")
		annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
		testCompileOnly("org.projectlombok:lombok:${lombokVersion}")
		testAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
		testFixturesCompileOnly("org.projectlombok:lombok:${lombokVersion}")
		testFixturesAnnotationProcessor("org.projectlombok:lombok:${lombokVersion}")
	}

	tasks.named<BootJar>("bootJar") {
		enabled = false
	}

	tasks.named<Jar>("jar") {
		enabled = true
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

project(":api") {
	/*
    'src/main/java' 디렉토리를 test 소스셋의 자바 소스 디렉터리로 추가
    sourceSets를 지정하지 않으면 api test 모듈이 api main 모듈을 인식하지 못함
    */
	sourceSets {
		test {
			java {
				srcDir("src/main/java")
			}
		}
	}
}
