import org.springframework.boot.gradle.tasks.bundling.BootJar

tasks.named<BootJar>("bootJar") {
//    dependsOn("buildFrontend")
    enabled = true
    mainClass.set("com.youtube-clone.api.CloneApplication")
}

tasks.named<Jar>("jar") {
    enabled = false
}

val restAssuredVersion: String by project
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.security:spring-security-crypto")
    implementation("org.springframework:spring-tx")
//    implementation("org.springframework.boot:spring-boot-starter-security")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
//    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation(project(":core"))
//    implementation(project(":support:monitoring"))
//    implementation(project(":support:logging"))
//    implementation(project(":crawl-batch"))

    testImplementation("io.rest-assured:rest-assured:${restAssuredVersion}")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
//    testImplementation("org.springframework.security:spring-security-test")
    testImplementation(testFixtures(project(":core")))
}