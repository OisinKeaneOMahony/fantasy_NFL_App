plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.setu.fantasyApp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.slf4j:slf4j-simple:1.6.1")
    implementation("io.github.microutils:kotlin-logging:1.6.22")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}